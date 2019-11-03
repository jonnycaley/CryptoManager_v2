package com.example.cryptomanager_v2.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.component
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SplashViewModelFactory

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)

        viewModel.exchangeRates.observe(this, Observer { exchangeRates ->
            text_loading.text = exchangeRates.toString()
        })
    }
}