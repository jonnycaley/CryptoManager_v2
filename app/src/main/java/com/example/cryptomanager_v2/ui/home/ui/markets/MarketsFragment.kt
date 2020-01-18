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
import javax.inject.Inject

class MarketsFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: MarketsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory)[MarketsViewModel::class.java]

        viewModel.marketsData.observe(viewLifecycleOwner, Observer {
            it
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_markets, container, false)
    }
}