package com.example.moviecatalog.domain.usecase

import androidx.compose.ui.unit.Constraints
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.validator.PasswordValidator
import com.example.moviecatalog.domain.validator.Validator

class DataValidateUseCase {
    fun invoke(
        validator: Validator,
        data: String,
        secondData: String = Constants.EMPTY_STRING
    ): Int? {
        return validator.validate(data, secondData)
    }
}