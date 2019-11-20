package com.example.cryptomanager_v2.ui.splash

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule var activitySenarioRule = ActivityScenarioRule<SplashActivity>(SplashActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun test() {
        val senario = activitySenarioRule.scenario
        SplashActivityRobot()
            .checkLoadingTextIsIdle()
    }
}