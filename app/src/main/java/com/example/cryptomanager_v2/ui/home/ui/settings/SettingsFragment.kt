package com.example.cryptomanager_v2.ui.home.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptomanager_v2.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var settingsEpoxyController: SettingsEpoxyController

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory)[SettingsViewModel::class.java]

        epoxy_recycler_settings.apply {
            setController(settingsEpoxyController)
        }

        viewModel.settingsData.observe(this, Observer {
            loadSettings(it)
        })
    }

    private fun loadSettings(settingsData: SettingsData) {
        settingsEpoxyController.setData(settingsData)
    }
}
