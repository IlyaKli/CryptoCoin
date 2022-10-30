package com.ilya.cryptocoin.presentation

import android.app.Application
import androidx.work.Configuration
import com.ilya.cryptocoin.data.di.DaggerApplicationComponent
import com.ilya.cryptocoin.data.workers.CoinWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().crate(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}