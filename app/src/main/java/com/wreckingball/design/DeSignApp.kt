package com.wreckingball.design

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.wreckingball.design.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DeSignApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DeSignApp)
            modules(appModule)
        }

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error)
        }
    }
}