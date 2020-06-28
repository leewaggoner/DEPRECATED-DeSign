package com.wreckingball.design.ui.auth

import androidx.lifecycle.ViewModel
import com.wreckingball.design.components.Authentication

class LoginViewModel(private val authentication: Authentication) : ViewModel() {
    val resetPassword = authentication.resetPassword
    val passwordConfirmed = authentication.passwordConfirmed
    val registrationComplete = authentication.registrationComplete
    val isLoggedIn = authentication.isLoggedIn
    val forgotPasswordSent = authentication.forgotPasswordSent
    val registrationSent = authentication.registrationSent

    fun handleResetPasswordClick(existingPassword: String, newPassword: String) : Boolean {
        if (authentication.validPassword(existingPassword)
            && authentication.validPassword(newPassword)) {
            authentication.resetPassword(existingPassword, newPassword)
            return true
        }
        return false
    }

    fun handleConfirmPasswordClick(confirmationCode: String, password: String) : Boolean {
        if (confirmationCode.isNotEmpty() && authentication.validPassword(password)) {
            authentication.confirmPassword(confirmationCode, password)
            return true
        }
        return false
    }

    fun handleConfirmSignUpClick(confirmationCode: String) : Boolean {
        if (confirmationCode.isNotEmpty()) {
            authentication.confirmRegistration(confirmationCode)
            return true
        }
        return false
    }

    fun handleSignInClick(userName: String, password: String) : Boolean {
        if (authentication.validUsername(userName) && authentication.validPassword(password)) {
            authentication.signIn(userName, password)
            return true
        }
        return false
    }

    fun handleForgotPasswordClick() {
        authentication.forgotPassword()
    }

    fun handleSignUpClick(userName: String, password: String, email: String) : Boolean {
        if (authentication.validUsername(userName)
            && authentication.validPassword(password)
            && authentication.validEmail(email)) {
            authentication.registerUser(userName, password, email)
            return true
        }
        return false
    }
}