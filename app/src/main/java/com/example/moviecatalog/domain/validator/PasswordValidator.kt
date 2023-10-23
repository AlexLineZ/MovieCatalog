package com.example.moviecatalog.domain.validator

import com.example.moviecatalog.common.ErrorMapper
import java.util.regex.Pattern

class PasswordValidator : Validator {
    override fun validate(data: String, secondData: String): Int? {
        val pattern = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$"
        val regex = Pattern.compile(pattern)

        return when {
            data.isEmpty() -> null
            data.length < 8 -> ErrorMapper.ERROR_PASSWORD_LENGTH
            !regex.matcher(data).matches() -> ErrorMapper.ERROR_PASSWORD_LETTERS
            else -> null
        }
    }
}