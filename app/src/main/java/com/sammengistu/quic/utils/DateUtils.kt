package com.sammengistu.quic.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "DateUtils"
private const val ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val APP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
class DateUtils {

    companion object {
        fun convertUTCDate(utcDate: String?): String {
            if (utcDate.isNullOrBlank()) return ""

            return try {
                val dateFormat = SimpleDateFormat(ISO_FORMAT, Locale.US)
                dateFormat.timeZone = TimeZone.getTimeZone("UTC")
                val date = dateFormat.parse(utcDate)
                val outputFmt = SimpleDateFormat("MMM dd, yyy", Locale.US)
                outputFmt.format(date)
            } catch (e: ParseException) {
                ""
            }
        }
    }
}