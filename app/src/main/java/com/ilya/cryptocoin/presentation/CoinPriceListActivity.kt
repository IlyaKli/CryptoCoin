package com.ilya.cryptocoin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ilya.cryptocoin.R
import com.ilya.cryptocoin.databinding.ActivityCoinPrceListBinding
import com.ilya.cryptocoin.domain.CoinInfo
import com.ilya.cryptocoin.presentation.adapters.CoinInfoAdapter
import kotlinx.android.synthetic.main.activity_coin_prce_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinPrceListBinding.inflate(layoutInflater) }

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}