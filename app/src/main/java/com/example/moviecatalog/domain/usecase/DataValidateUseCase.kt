package com.example.moviecatalog.domain.usecase

import com.example.moviecatalog.domain.validator.PasswordValidator
import com.example.moviecatalog.domain.validator.Validator

class DataValidateUseCase {
    fun invoke (validator: Validator, data: String): Int? {
        return validator.validate(data)
    }

    fun checkConfirmPassword (
        validator: PasswordValidator,
        password: String,
        confirm: String
    ): Int? {
        return validator.isEqualityPassword(password, confirm)
    }
}