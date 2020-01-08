package com.example.cryptomanager_v2.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.cryptomanager_v2.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerAppCompatActivity

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupNavController()
    }

    private fun setupNavController() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    /*
    Function handles the back pressed button and stops it from going back to the splash screen
    */
    override fun onBackPressed() { }

    companion object {
        fun create(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}
