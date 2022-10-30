package com.ilya.cryptocoin.presentation

import android.app.Application
import com.ilya.cryptocoin.data.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().crate(this)
    }
}