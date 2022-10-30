package com.ilya.cryptocoin.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.ilya.cryptocoin.data.database.AppDatabase
import com.ilya.cryptocoin.data.database.CoinInfoDao
import com.ilya.cryptocoin.data.mapper.CoinMapper
import com.ilya.cryptocoin.data.network.model.CoinInfoDto
import com.ilya.cryptocoin.data.workers.CoinInfoWorker
import com.ilya.cryptocoin.domain.CoinInfo
import com.ilya.cryptocoin.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val application: Application
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)

            workManager.enqueueUniqueWork(
            CoinInfoWorker.NAME_WORKER,
            ExistingWorkPolicy.REPLACE,
            CoinInfoWorker.workRequest()
        )
    }
}