package dev.giussepr.jetpackcompose.booking.model

data class Hotel(
    val name: String,
    val location: String,
    val rate: Double,
    val reviews: String,
    val pricePerNight: Double,
    val tags: List<Tag>,
    val description: String,
    val offer: List<Offer>
)
