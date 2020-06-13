package com.wreckingball.design.auth

import com.wreckingball.design.utils.PreferencesWrapper

private const val USER_NAME_KEY = "userName"

class Authentication(private val preferencesWrapper: PreferencesWrapper) {
    var loggedIn: Boolean = false
        get() {
            val name = preferencesWrapper.getString(USER_NAME_KEY, "")
            return if (name!!.isNotEmpty() || !field)
                preferencesWrapper.getBoolean(name, false)
            else
                field
        }
    private var userName: String = ""

    fun authenticateUser(userName: String, password: String) : Boolean {
        //validate user name
        val name = preferencesWrapper.getString(USER_NAME_KEY, "")
        name?.let {
            if (name !== userName) {
                preferencesWrapper.putString(name, "")
                preferencesWrapper.putString(USER_NAME_KEY, userName)
                this.userName = userName
            }
        }

        //log 'em in
        loggedIn = true
        preferencesWrapper.putBoolean(userName, true)
        return true
    }
}