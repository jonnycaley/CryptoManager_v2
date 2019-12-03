package com.example.cryptomanager_v2.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.ui.home.HomeActivity
import com.example.cryptomanager_v2.utils.Status
import com.example.cryptomanager_v2.utils.exhaustive
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class SplashActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this, factory)[SplashViewModel::class.java]

        viewModel.apply {
            status.observe(this@SplashActivity, Observer { status ->
                text_retry.visibility = View.GONE
                progress_bar.visibility = View.GONE
                when(status) {
                    is Status.LOADING -> {
                        onLoading()
                    }
                    is Status.ERROR -> {
                        onError(status)
                    }
                    is Status.SUCCESS -> {
                        onSuccess()
                    }
                    is Status.IDLE -> { }
                }.exhaustive
            })
            text_retry.setOnClickListener {
                viewModel.retry()
            }
        }
    }

    private fun onSuccess() {
        startActivity(HomeActivity.create(this@SplashActivity))
    }

    private fun onError(status: Status.ERROR) {
        text_loading.visibility = View.VISIBLE
        text_loading.text = status.reason
        text_retry.visibility = View.VISIBLE
    }

    private fun onLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}