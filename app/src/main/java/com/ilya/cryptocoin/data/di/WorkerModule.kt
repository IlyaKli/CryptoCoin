package com.ilya.cryptocoin.data.di

import androidx.work.ListenableWorker
import com.ilya.cryptocoin.data.workers.ChildWorkerFactory
import com.ilya.cryptocoin.data.workers.CoinInfoWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(CoinInfoWorker::class)
    fun bindCoinInfoWorkerFactory(worker: CoinInfoWorker.Factory): ChildWorkerFactory
}