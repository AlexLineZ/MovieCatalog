package com.example.moviecatalog.domain.validator

import com.example.moviecatalog.common.ErrorMapper
import java.util.regex.Pattern

class ConfirmPasswordValidator : Validator {
    override fun validate(data: String, secondData: String): Int? {
        return when {
            data != secondData -> ErrorMapper.ERROR_PASSWORD_EQUALITY
            else -> null
        }
    }
}