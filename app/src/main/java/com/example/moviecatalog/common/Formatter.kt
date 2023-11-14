package com.example.moviecatalog.common

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import java.time.ZoneId
import java.util.Date
import java.util.Locale

object Formatter {
    fun splitNumber(number: String): String {
        val reversedNumber = number.reversed()
        val builder = StringBuilder()

        for ((index, char) in reversedNumber.withIndex()) {
            builder.append(char)
            if ((index + 1) % 3 == 0 && index + 1 != reversedNumber.length) {
                builder.append(" ")
            }
        }

        return builder.reverse().toString()
    }

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

    fun formatDateToNormal(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }
}