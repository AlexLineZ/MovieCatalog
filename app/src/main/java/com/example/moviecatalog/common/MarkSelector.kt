package com.example.moviecatalog.common

import androidx.compose.ui.graphics.Color
import com.example.moviecatalog.data.model.Mark
import com.example.moviecatalog.domain.model.movie.Review
import com.example.moviecatalog.presentation.ui.theme.BadMarkColor
import com.example.moviecatalog.presentation.ui.theme.GoodMarkColor
import com.example.moviecatalog.presentation.ui.theme.MiddleMarkColor
import com.example.moviecatalog.presentation.ui.theme.NormalMarkColor
import com.example.moviecatalog.presentation.ui.theme.UnderMiddleMarkColor
import com.example.moviecatalog.presentation.ui.theme.UpperBadMarkColor

object MarkSelector {
    fun markCalculation(list: ArrayList<Review>) : Mark {
        var markCount = 0.0f
        list.forEach{
            review ->
            markCount += review.rating
        }
        val average = markCount / list.size
        val mark = String.format("%.1f", average)
        return Mark (
            mark = changeNumberFormat(mark),
            color = setColorForMark(average)
        )
    }

    fun setColorForMark(mark: Float): Color {
        return when {
            mark in 9f..10f -> {
                GoodMarkColor
            }

            mark >= 7f && mark < 9f -> {
                NormalMarkColor
            }

            mark >= 6f && mark < 7f -> {
                MiddleMarkColor
            }

            mark >= 4f && mark < 6f -> {
                UnderMiddleMarkColor
            }

            mark >= 3f && mark < 4f -> {
                UpperBadMarkColor
            }

            mark >= 0f && mark < 3f -> {
                BadMarkColor
            }

            else -> {
                BadMarkColor
            }
        }
    }

    private fun changeNumberFormat(mark: String): String {
        return mark.replace(",", ".")
    }
}