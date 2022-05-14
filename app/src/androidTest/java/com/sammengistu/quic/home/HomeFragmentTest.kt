package com.sammengistu.quic.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sammengistu.quic.R
import com.sammengistu.quic.helpers.launchFragmentInHiltContainer
import com.sammengistu.quic.ui.home.fragments.HomeFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun dataDisabledTest() {
        val homeFragment = HomeFragment().apply { disableDataFetching = true }
        launchFragmentInHiltContainer(homeFragment)
        onView(withId(R.id.error_message)).check(matches(isDisplayed()))
    }
}