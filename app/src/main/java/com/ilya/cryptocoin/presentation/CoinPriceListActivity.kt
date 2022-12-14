package com.ilya.cryptocoin.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ilya.cryptocoin.R
import com.ilya.cryptocoin.databinding.ActivityCoinPrceListBinding
import com.ilya.cryptocoin.domain.CoinInfo
import com.ilya.cryptocoin.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val binding by lazy { ActivityCoinPrceListBinding.inflate(layoutInflater) }
    private val adapter by lazy { CoinInfoAdapter(this) }

    private val component by lazy { (application as CoinApp).component }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setCoinInfoRecyclerView()
        observerViewModel()
    }

    private fun isHorizontalOrientation(): Boolean {
        return binding.coinDetailContainer != null
    }

    private fun observerViewModel() {
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun setCoinInfoRecyclerView() {
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        adapter.onCoinClickListener = {
            coinClickListener(it)
        }
    }

    private fun launchCoinDetailFragment(it: CoinInfo) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.coinDetailContainer, CoinDetailFragment.newInstance(it.fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun startCoinDetailActivity(it: CoinInfo) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            it
        )
        startActivity(intent)
    }

    private fun coinClickListener(it: CoinInfo) {
        if (isHorizontalOrientation()) {
            launchCoinDetailFragment(it)
        } else {
            startCoinDetailActivity(it)
        }
    }
}