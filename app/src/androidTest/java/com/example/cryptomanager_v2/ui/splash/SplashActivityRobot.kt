package com.example.cryptomanager_v2.ui.splash

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

class SplashActivityRobot {

    fun checkLoadingTextIsIdle(): SplashActivityRobot {
        checkLoadingText("Idle...")
        return this
    }

    private fun checkLoadingText(text: String): SplashActivityRobot {
        onView(withText(text))
            .check(matches(isDisplayed()))
        return this
    }
}