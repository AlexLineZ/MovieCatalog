package com.example.moviecatalog.common

import android.icu.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.Locale

fun formatDate(date: Date?): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return if (date != null) {
        val utilDate = Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toInstant())
        formatter.format(utilDate)
    } else {
        ""
    }
}