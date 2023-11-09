package com.example.moviecatalog.data.model

import android.os.Parcelable
import com.example.moviecatalog.common.Constants.EMPTY_STRING
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentReview(
    val movieId: String = EMPTY_STRING,
    val userRating: Int? = null
) : Parcelable
