package com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SelectBaseFiatActivity: DaggerAppCompatActivity(), SelectBaseFiatController.SelectBaseFiatCallback {

    @Inject
    lateinit var factory: SelectBaseFiatViewModel.Factory

    private lateinit var viewModel: SelectBaseFiatViewModel

    @Inject
    lateinit var controller: SelectBaseFiatController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_base_fiat)

        controller.callback = this

        viewModel = factory.create(this)
        viewModel.fiats.observe(this, Observer {
            controller.setData(it)
        })
    }

    companion object {
        fun create(context: Context): Intent {
            val intent = Intent(context, SelectBaseFiatActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
    }

    override fun setBaseFiat(it: DBFiat) {
        println("Set base fiat: ${it.name}")
    }
}