package com.example.cryptomanager_v2.ui.home.ui.markets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_markets.*
import javax.inject.Inject

class MarketsFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var marketsEpoxyController: MarketsEpoxyController

    lateinit var viewModel: MarketsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        epoxy_recycler_markets.setController(marketsEpoxyController)

        viewModel = ViewModelProviders.of(this, factory)[MarketsViewModel::class.java]
        viewModel.marketsData.observe(this, Observer {
            updateMarketsDataState(it)
        })
    }

    private fun updateMarketsDataState(state: MarketsData) {
        showData(state)
//        when (state.status) {
//            is Status.ERROR -> {
//
//            }
//            is Status.LOADING -> {
//
//            }
//            is Status.IDLE -> {
//
//            }
//            is Status.SUCCESS -> {
//                state.data?.let { top100 ->
//                    showTop100(top100)
//                }
//            }
//        }.exhaustive
    }
    private fun showData(data: MarketsData?) {
        data?.let {
            marketsEpoxyController.setData(data)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_markets, container, false)
    }
}