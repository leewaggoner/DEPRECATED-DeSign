package com.wreckingball.design.utils

import android.content.SharedPreferences

class PreferencesWrapper(sharedPreferences: SharedPreferences) {
    private val preferences = sharedPreferences

    fun getString(key: String, default: String) : String? {
        return preferences.getString(key, default)
    }

    fun putString(key: String, value: String) {
        preferences.edit().also {ed ->
            ed.putString(key, value)
            ed.apply()
        }
    }

    fun getInt(key: String, default: Int) : Int {
        return preferences.getInt(key, default)
    }

    fun putInt(key: String, value: Int) {
        preferences.edit().also {ed ->
            ed.putInt(key, value)
            ed.apply()
        }
    }

    fun getBoolean(key: String, default: Boolean) : Boolean {
        return preferences.getBoolean(key, default)
    }

    fun putBoolean(key: String, value: Boolean) {
        preferences.edit().also {ed ->
            ed.putBoolean(key, value)
            ed.apply()
        }
    }
}