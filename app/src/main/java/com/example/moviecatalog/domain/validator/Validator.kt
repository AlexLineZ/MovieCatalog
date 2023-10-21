package com.example.moviecatalog.domain.validator

interface Validator {
    fun validate(data: String): Int?
}