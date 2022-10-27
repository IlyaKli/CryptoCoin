package com.ilya.cryptocoin.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ilya.cryptocoin.R
import com.ilya.cryptocoin.databinding.ActivityCoinDetailBinding
import com.ilya.cryptocoin.domain.CoinInfo

class CoinDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val coin = intent.getParcelableExtra<CoinInfo>(EXTRA_COIN_INFO)
            ?: throw RuntimeException("Coin extra == null")
        if (savedInstanceState == null) {
            launchFragment(coin)
        }
    }

    private fun launchFragment(it: CoinInfo) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.detailContainer, CoinDetailFragment.newInstance(it.fromSymbol))
            .commit()
    }

    companion object {
        private const val EXTRA_COIN_INFO = "coin_info"

        fun newIntent(context: Context, coin: CoinInfo): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_COIN_INFO, coin)
            return intent
        }
    }
}