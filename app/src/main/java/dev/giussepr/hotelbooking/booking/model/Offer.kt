package dev.giussepr.hotelbooking.booking.model

import androidx.annotation.DrawableRes

data class Offer(
    @DrawableRes val resId: Int,
    val name: String
)
