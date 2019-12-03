package com.example.cryptomanager_v2.ui.splash

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.ui.home.HomeActivity

class SplashActivityRobot {

    fun checkHasNavigatedToHomeActivity(): SplashActivityRobot {
        intended(hasComponent(HomeActivity::class.java.name))
        return this
    }

    fun clickRetry(): SplashActivityRobot {
        onView(withId(R.id.text_retry))
            .perform(click())
        return this
    }

    fun checkLoadingText(text: String): SplashActivityRobot {
        onView(withId(R.id.text_loading))
            .check(matches(withText(text)))
        return this
    }
}