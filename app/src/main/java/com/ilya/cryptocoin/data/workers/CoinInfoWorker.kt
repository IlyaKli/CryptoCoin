package com.ilya.cryptocoin.data.workers

import android.content.Context
import androidx.work.*
import com.ilya.cryptocoin.data.database.AppDatabase
import com.ilya.cryptocoin.data.database.CoinInfoDao
import com.ilya.cryptocoin.data.mapper.CoinMapper
import com.ilya.cryptocoin.data.network.ApiFactory
import com.ilya.cryptocoin.data.network.ApiService
import kotlinx.coroutines.delay

class CoinInfoWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinMapper
) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }

    companion object {
        const val NAME_WORKER = "coin_info_worker"

        fun workRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<CoinInfoWorker>().build()
        }
    }
}