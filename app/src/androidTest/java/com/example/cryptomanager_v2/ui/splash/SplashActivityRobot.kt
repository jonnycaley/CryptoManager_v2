package com.example.cryptomanager_v2.ui.splash

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.cryptomanager_v2.R
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.example.cryptomanager_v2.ui.home.HomeActivity

class SplashActivityRobot {

    fun checkLoadingTextIsError(): SplashActivityRobot {
        checkLoadingText("Idle...")
        return this
    }

    fun checkLoadingTextIsSuccess(): SplashActivityRobot {
        checkLoadingText("Success!")
        return this
    }

    fun checkHasNavigatedToHomeActivity(): SplashActivityRobot {
        intended(hasComponent(HomeActivity::class.java.name))
        return this
    }

    fun checkLoadingTextIsLoading(): SplashActivityRobot {
        checkLoadingText("Loading...")
        return this
    }

    fun checkLoadingText(text: String): SplashActivityRobot {
        onView(withId(R.id.text_loading))
            .check(matches(withText(text)))
        return this
    }
}