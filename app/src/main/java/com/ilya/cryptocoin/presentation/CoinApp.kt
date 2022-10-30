package com.ilya.cryptocoin.presentation

import android.app.Application
import androidx.work.Configuration
import com.ilya.cryptocoin.data.database.AppDatabase
import com.ilya.cryptocoin.data.di.DaggerApplicationComponent
import com.ilya.cryptocoin.data.mapper.CoinMapper
import com.ilya.cryptocoin.data.network.ApiFactory
import com.ilya.cryptocoin.data.workers.CoinInfoWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinInfoWorkerFactory

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