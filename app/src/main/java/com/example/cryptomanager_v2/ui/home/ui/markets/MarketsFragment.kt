package com.example.cryptomanager_v2.ui.home.ui.markets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R

class MarketsFragment : Fragment() {
    private lateinit var marketsViewModel: MarketsViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        marketsViewModel =
            ViewModelProviders.of(this).get(MarketsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_markets, container, false)
        val textView: TextView = root.findViewById(R.id.text_markets)
        marketsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}