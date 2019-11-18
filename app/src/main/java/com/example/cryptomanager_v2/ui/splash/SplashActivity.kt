package com.example.cryptomanager_v2.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.ui.home.HomeActivity
import com.example.cryptomanager_v2.utils.Status
import com.example.cryptomanager_v2.utils.exhaustive
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

@Suppress("IMPLICIT_CAST_TO_ANY")
class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: SplashViewModelFactory

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupToolbar()

        setContentView(R.layout.activity_splash)

        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)

        viewModel.apply {
            status.observe(this@SplashActivity, Observer { status ->
                text_retry.visibility = View.GONE
                progress_bar.visibility = View.GONE
                when(status) {
                    is Status.LOADING -> {
                        text_loading.text = "Loading data..."
                        progress_bar.visibility = View.VISIBLE
                    }
                    is Status.ERROR -> {
                        text_loading.text = status.reason
                        text_retry.visibility = View.VISIBLE
                    }
                    is Status.SUCCESS -> {
                        text_loading.text = "Success!"
                        HomeActivity.create(this@SplashActivity)
                    }
                    is Status.IDLE -> {
                        text_loading.text = "Idle..."
                    }
                }
            })
            text_retry.setOnClickListener {
                viewModel.retry()
            }
        }
    }
    private fun setupToolbar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}