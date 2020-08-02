package com.wreckingball.design.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.wreckingball.design.components.Authentication
import com.wreckingball.design.components.CampaignSigns
import com.wreckingball.design.repositories.CampaignRepository
import com.wreckingball.design.repositories.SignRepository
import com.wreckingball.design.ui.auth.LoginViewModel
import com.wreckingball.design.ui.campaigns.CampaignsViewModel
import com.wreckingball.design.ui.dashboard.DashboardViewModel
import com.wreckingball.design.ui.map.MapViewModel
import com.wreckingball.design.utils.PreferencesWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module(override = true) {
    viewModel { MapViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { CampaignsViewModel(get()) }
    factory { Authentication(get()) }
    factory { CampaignSigns(get(), get()) }
    single { SignRepository(get()) }
    single { CampaignRepository(get()) }
    single { PreferencesWrapper(getSharedPrefs(androidContext())) }
}

private fun getSharedPrefs(context: Context) : SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}
