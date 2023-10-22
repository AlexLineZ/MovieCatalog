package com.example.moviecatalog.domain.validator
import com.example.moviecatalog.common.ErrorMapper

class EmailValidator : Validator {
    override fun validate(data: String, secondData: String) : Int?{
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+")

        return if (data.isEmpty() || data == null) {
            null
        } else if (!emailPattern.matches(data)) {
            ErrorMapper.EMAIL_ERROR
        } else null
    }
}