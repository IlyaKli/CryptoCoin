package com.ilya.cryptocoin.data.di

import android.app.Application
import com.ilya.cryptocoin.presentation.CoinApp
import com.ilya.cryptocoin.presentation.CoinDetailFragment
import com.ilya.cryptocoin.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun crate(@BindsInstance application: Application): ApplicationComponent
    }
}