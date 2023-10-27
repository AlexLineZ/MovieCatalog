package com.example.moviecatalog.common

import com.example.moviecatalog.domain.model.movie.Review
import com.example.moviecatalog.presentation.ui.theme.MiddleMarkColor

object Helper {
    fun markCalculation(list: ArrayList<Review>) : Mark {
        var markCount = 0.0f
        list.forEach{
            review ->
            markCount += review.rating
        }
        val average = markCount / list.size
        val mark = String.format("%.1f", average)
        return Mark (
            mark = mark,
            color = MiddleMarkColor
        )
    }
}