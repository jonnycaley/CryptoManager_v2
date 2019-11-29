package com.example.cryptomanager_v2.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.ui.home.HomeActivity
import com.example.cryptomanager_v2.utils.Status
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
                        showLoading()
                    }
                    is Status.ERROR -> {
                        showError(status)
                    }
                    is Status.SUCCESS -> {
                        showSuccess()
                    }
                    is Status.IDLE -> {
                        showIdle()
                    }
                }
            })
            text_retry.setOnClickListener {
                viewModel.retry()
            }
        }
    }
    private fun showIdle() {
        text_loading.text = "Idle..."
    }

    private fun showSuccess() {
        text_loading.text = "Success!"
        startActivity(HomeActivity.create(this@SplashActivity))
    }

    private fun showError(status: Status.ERROR) {
        text_loading.text = status.reason
        text_retry.visibility = View.VISIBLE
    }

    private fun showLoading() {
        text_loading.text = "Loading data..."
        progress_bar.visibility = View.VISIBLE
    }

    private fun setupToolbar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}