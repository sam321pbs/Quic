package com.sammengistu.quic.testing

import androidx.test.espresso.idling.CountingIdlingResource


object IdlingResources {
    val newsIdlingResource = CountingIdlingResource("News")
    val financeIdlingResource = CountingIdlingResource("Finance")
    val weatherIdlingResource = CountingIdlingResource("Weather")
}