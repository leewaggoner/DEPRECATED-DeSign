package com.wreckingball.design.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.wreckingball.design.utils.PreferencesWrapper

const val USERNAME_KEY = "username"

private const val TAG = "Authentication"

class Authentication(private val preferencesWrapper: PreferencesWrapper) {
    val isLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val registrationSent: MutableLiveData<Boolean> = MutableLiveData()
    val registrationComplete: MutableLiveData<Boolean> = MutableLiveData()
    val signedOut: MutableLiveData<Boolean> = MutableLiveData()
    val forgotPasswordSent: MutableLiveData<Boolean> = MutableLiveData()
    val passwordConfirmed: MutableLiveData<Boolean> = MutableLiveData()
    val resetPassword: MutableLiveData<Boolean> = MutableLiveData()
    var errorMsg = ""
    private lateinit var username: String

    fun checkAuthenticationStatus() {
        Amplify.Auth.fetchAuthSession(
            { result ->
                Log.i(TAG, result.toString())
                if (!result.isSignedIn) {
                    errorMsg = result.toString()
                }
                isLoggedIn.postValue( result.isSignedIn)
            },
            { error ->
                Log.e(TAG, error.toString())
                errorMsg = error.toString()
                isLoggedIn.postValue(false)
            }
        )
    }

    fun registerUser(username: String, password: String, email: String) {
        Amplify.Auth.signUp(
            username,
            password,
            AuthSignUpOptions.builder().userAttribute(AuthUserAttributeKey.email(), email).build(),
            { result ->
                Log.i(TAG, "Result: $result")
                if (!result.isSignUpComplete) {
                    errorMsg = result.toString()
                } else {
                    this.username = username
                    preferencesWrapper.putString(USERNAME_KEY, username)
                }
                registrationSent.postValue(result.isSignUpComplete)
            },
            { error ->
                Log.e(TAG, "Sign up failed", error)
                errorMsg = error.toString()
                registrationSent.postValue(false)
            }
        )
    }

    fun validUsername(username: String): Boolean {
        val regex = """^(?=.{3,15}${'$'})[A-Za-z0-9]+(?:[_-][A-Za-z0-9]+)*$""".toRegex()
        val valid = regex.matches(input = username)
        return valid
    }

    fun validEmail(email: String): Boolean {
        val valid = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        return valid
    }

    fun validPassword(password: String): Boolean {
        val regex = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#${'$'}^+=!*()@%&]).{8,}$""".toRegex()
        val valid = regex.matches(input = password)
        return valid
    }

    fun confirmRegistration(code: String) {
        if (code.isNotEmpty()) {
            Amplify.Auth.confirmSignUp(
                username,
                code,
                { result ->
                    if (!result.isSignUpComplete) {
                        Log.i(TAG, result.toString())
                        errorMsg = result.toString()
                    }
                    isLoggedIn.postValue(false)
                    registrationComplete.postValue(result.isSignUpComplete)
                },
                { error ->
                    Log.e("AuthQuickstart", error.toString())
                    errorMsg = error.toString()
                    registrationComplete.postValue(false)
                }
            )
        }
    }

    fun signIn(username: String, password: String) {
        Amplify.Auth.signIn(
            username,
            password,
            { result ->
                if (!result.isSignInComplete) {
                    Log.i(TAG, result.toString())
                    errorMsg = result.toString()
                }
                isLoggedIn.postValue(result.isSignInComplete)
            },
            { error ->
                Log.e(TAG, error.toString())
                errorMsg = error.toString()
                isLoggedIn.postValue(false)
            }
        )
    }

    fun signOut() {
        Amplify.Auth.signOut(
            {
                Log.i(TAG, "Signed out successfully")
                signedOut.postValue(true)
            },
            { error ->
                Log.e(TAG, error.toString())
                errorMsg = error.toString()
                signedOut.postValue(false)
            }
        )
    }

    fun forgotPassword() {
        val username = preferencesWrapper.getString(USERNAME_KEY, "")
        username?.let {name->
            if (name.isNotEmpty()) {
                Amplify.Auth.resetPassword(
                    name,
                    {
                        forgotPasswordSent.postValue(true)
                    },
                    { error ->
                        Log.e(TAG, error.toString())
                        errorMsg = error.toString()
                        forgotPasswordSent.postValue(false)
                    }
                )
            }
        }
    }

    fun confirmPassword(code: String, password: String) {
        Amplify.Auth.confirmResetPassword(
            password,
            code,
            {
                Log.i(TAG, "New password confirmed")
                passwordConfirmed.postValue(true)

            },
            { error ->
                Log.e(TAG, error.toString())
                errorMsg = error.toString()
                passwordConfirmed.postValue(false)
            }
        )
    }

    fun resetPassword(existingPassword: String, newPassword: String) {
        Amplify.Auth.updatePassword(
            existingPassword,
            newPassword,
            {
                Log.i(TAG, "Updated password successfully")
                resetPassword.postValue(true)
            },
            {
                error -> Log.e("AuthQuickstart", error.toString())
                errorMsg = error.toString()
                resetPassword.postValue(false)
            }
        )
    }
}