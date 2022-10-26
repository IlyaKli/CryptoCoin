package com.ilya.cryptocoin.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ilya.cryptocoin.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso


class CoinDetailFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[CoinViewModel::class.java] }

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinDetailBinding == null")

    private var fromSymbol: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fromSymbol = it.getString(EXTRA_FROM_SYMBOL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailInfo(fromSymbol!!).observe(viewLifecycleOwner) {
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowDay
                tvMaxPrice.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
            }
            Picasso.get().load(it.imageUrl).into(binding.ivLogoCoin)
        }
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "from_symbol"

        fun newInstance(fromSymbol: String) =
            CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
    }
}