package com.example.cryptomanager_v2.ui.home.ui.markets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.utils.Status
import javax.inject.Inject

class MarketsFragment : Fragment() {
    @Inject
    lateinit var marketsViewModel: MarketsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        marketsViewModel.marketsData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                is Status.SUCCESS -> TODO()
                is Status.ERROR -> TODO()
                is Status.LOADING -> TODO()
                is Status.IDLE -> TODO()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_markets, container, false)
    }
}