package com.example.moviecatalog.domain.validator

import com.example.moviecatalog.R
import java.util.regex.Pattern

class PasswordValidator : Validator {
    override fun validate(data: String, secondData: String): Int? {
        val pattern = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$"
        val regex = Pattern.compile(pattern)

        return when {
            data.isEmpty() -> null
            data.length < 8 -> R.string.password_result1
            !regex.matcher(data).matches() -> R.string.password_result2
            else -> null
        }
    }
}