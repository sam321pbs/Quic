package com.sammengistu.quic

import com.sammengistu.quic.utils.DateUtils
import org.junit.Test

import org.junit.Assert.assertEquals

class DateUtilsTest {
    @Test
    fun convertDateTest() {
        val dateUTC = "2022-04-01T13:57:31Z"

        assertEquals("Apr 01, 2022", DateUtils.convertUTCDate(dateUTC))
    }
}