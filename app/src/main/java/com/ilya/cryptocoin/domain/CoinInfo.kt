package com.ilya.cryptocoin.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: String,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String
): Parcelable