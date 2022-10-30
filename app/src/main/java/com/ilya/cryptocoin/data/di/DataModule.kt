package com.ilya.cryptocoin.data.di

import android.app.Application
import com.ilya.cryptocoin.data.database.AppDatabase
import com.ilya.cryptocoin.data.database.CoinInfoDao
import com.ilya.cryptocoin.data.network.ApiFactory
import com.ilya.cryptocoin.data.network.ApiService
import com.ilya.cryptocoin.data.repository.CoinRepositoryImpl
import com.ilya.cryptocoin.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(application: Application): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}