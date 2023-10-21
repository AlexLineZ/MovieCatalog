package com.example.moviecatalog.domain.validator

import com.example.moviecatalog.common.ErrorMapper

class EmailValidator : Validator {
    override fun validate(data: String) : Int?{
        val emailPattern = Regex("^[A-Za-z](.*)@(.+})(\\.)(.+)")

        return if (!emailPattern.matches(data)) {
            ErrorMapper.EMAIL_ERROR
        } else null
    }
}