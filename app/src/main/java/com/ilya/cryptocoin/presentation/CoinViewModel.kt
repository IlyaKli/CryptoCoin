package com.ilya.cryptocoin.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ilya.cryptocoin.data.repository.CoinRepositoryImpl
import com.ilya.cryptocoin.domain.GetCoinInfoListUseCase
import com.ilya.cryptocoin.domain.GetCoinInfoUseCase
import com.ilya.cryptocoin.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
            loadDataUseCase()
    }
}