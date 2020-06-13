package com.wreckingball.design.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.wreckingball.design.auth.Authentication
import com.wreckingball.design.repositories.SignRepository
import com.wreckingball.design.ui.home.HomeViewModel
import com.wreckingball.design.utils.PreferencesWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module(override = true) {
    viewModel { HomeViewModel(get()) }
    single { Authentication(get()) }
    single { PreferencesWrapper(getSharedPrefs(androidContext())) }
    single { SignRepository() }
}

private fun getSharedPrefs(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}
