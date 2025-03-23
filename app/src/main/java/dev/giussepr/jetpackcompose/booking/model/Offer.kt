package dev.giussepr.jetpackcompose.booking.model

import androidx.annotation.DrawableRes

data class Offer(
    @DrawableRes val resId: Int,
    val name: String
)
