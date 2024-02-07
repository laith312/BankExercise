package com.example.bankexercise

import android.app.Application
import com.example.bankexercise.di.appModule
import org.koin.core.context.startKoin

class BankExerciseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}