package com.ilya.cryptocoin.presentation

import android.app.Application
import androidx.work.Configuration
import com.ilya.cryptocoin.data.database.AppDatabase
import com.ilya.cryptocoin.data.di.DaggerApplicationComponent
import com.ilya.cryptocoin.data.mapper.CoinMapper
import com.ilya.cryptocoin.data.network.ApiFactory
import com.ilya.cryptocoin.data.workers.CoinInfoWorkerFactory

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().crate(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                CoinInfoWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}