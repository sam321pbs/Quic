package com.sammengistu.quic.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sammengistu.quic.R
import com.sammengistu.quic.helpers.atPosition
import com.sammengistu.quic.testing.IdlingResources
import com.sammengistu.quic.ui.home.activites.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class NewsEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(
            IdlingResources.newsIdlingResource
        )
    }

    @Test
    fun checkNewsArticleIsDisplayed() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView))
            .check(
                matches(atPosition(0, withId(R.id.article_card_view)))
            )

        onView(withId(R.id.recyclerView))
            .check(
                matches(atPosition(0, hasDescendant(withId(R.id.article_read_more_tv))))
            )
    }
}