package com.example.moviecatalog.domain.validator

import com.example.moviecatalog.common.Constants

interface Validator {
    fun validate(data: String, secondData: String = Constants.EMPTY_STRING): Int?
}