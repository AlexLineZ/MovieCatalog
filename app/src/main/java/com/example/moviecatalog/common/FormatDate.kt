package com.example.moviecatalog.common

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.time.ZoneId
import java.util.Date
import java.util.Locale

fun formatDate(date: Date?): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return if (date != null) {
        val utilDate = Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toInstant())
        formatter.format(utilDate)
    } else {
        Constants.EMPTY_STRING
    }
}

fun formatDateToISO8601(date: Date?): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return if (date != null) {
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        formatter.format(date)
    } else {
        Constants.EMPTY_STRING
    }
}