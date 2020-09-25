package com.ankushg.cocktailapp.android

import android.app.Application
import com.ankushg.cocktailapp.shared.initKoinAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class App : Application() {
    private val appModule: Module = module {
        viewModel { AndroidAppViewModel(appViewModel = get()) }
    }

    override fun onCreate() {
        super.onCreate()

        initKoinAndroid(appModule) {
            androidLogger()
            androidContext(this@App)
        }
    }
}