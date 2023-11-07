package com.example.moviecatalog.common

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
}