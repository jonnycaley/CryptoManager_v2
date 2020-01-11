package com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_select_base_fiat.*
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
        setupToolbar()

        controller.callback = this

        epoxy_recycler_select_base_fiat.setController(
            controller
        )

        viewModel = factory.create(this)
        viewModel.fiats.observe(this, Observer {
            controller.setData(it)
        })
    }
    private fun setupToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun setBaseFiat(it: DBFiat) {
        viewModel.setBaseFiat(it)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun create(context: Context): Intent {
            val intent = Intent(context, SelectBaseFiatActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }
    }
}