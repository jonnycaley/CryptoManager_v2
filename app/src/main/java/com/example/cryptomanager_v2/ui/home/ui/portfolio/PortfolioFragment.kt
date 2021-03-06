package com.example.cryptomanager_v2.ui.home.ui.portfolio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R

class PortfolioFragment : Fragment() {
    private lateinit var portfolioViewModel: PortfolioViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        portfolioViewModel = ViewModelProviders.of(this).get(PortfolioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_portfolio, container, false)
        val textView: TextView = root.findViewById(R.id.text_portfolio)
        portfolioViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}