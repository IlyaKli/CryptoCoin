package com.ilya.cryptocoin.presentation

import androidx.lifecycle.ViewModel
import com.ilya.cryptocoin.domain.GetCoinInfoListUseCase
import com.ilya.cryptocoin.domain.GetCoinInfoUseCase
import com.ilya.cryptocoin.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
        private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
        private val getCoinInfoUseCase: GetCoinInfoUseCase,
        private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
            loadDataUseCase()
    }
}