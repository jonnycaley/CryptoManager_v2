package com.example.cryptomanager_v2.ui.splash

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.cryptomanager_v2.R

class SplashActivityRobot {

    fun checkLoadingTextIsIdle(): SplashActivityRobot {
        checkLoadingText("Idle...")
        return this
    }

    fun checkLoadingTextIsSuccess(): SplashActivityRobot {
        checkLoadingText("Success!")
        return this
    }

    fun checkHasNavigatedToHomeActivity(): SplashActivityRobot {
        return this
    }

    fun checkLoadingTextIsLoading(): SplashActivityRobot {
        checkLoadingText("Loading...")
        return this
    }

    private fun checkLoadingText(text: String): SplashActivityRobot {
        onView(withId(R.id.text_loading))
            .check(matches(withText(text)))
        return this
    }
}