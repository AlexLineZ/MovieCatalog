package com.example.moviecatalog.domain.validator

import com.example.moviecatalog.R

class LoginValidator: Validator {
    override fun validate(data: String, secondData: String): Int? {
        val pattern = Regex("[a-zA-Z0-9 ]+")
        return if (data.isEmpty() || data == null) {
            null
        } else if (!pattern.matches(data)) {
            R.string.login_str_error
        } else null
    }

}