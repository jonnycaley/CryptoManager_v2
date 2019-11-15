package com.example.cryptomanager_v2.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: SplashViewModelFactory

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)

        viewModel.apply {
            status.observe(this@SplashActivity, Observer { status ->
                text_loading.text = status.toString()
            })
        }
    }
}