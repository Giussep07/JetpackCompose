package dev.giussepr.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.giussepr.jetpackcompose.booking.BookingScreen
import dev.giussepr.jetpackcompose.booking.model.Hotel
import dev.giussepr.jetpackcompose.booking.model.Offer
import dev.giussepr.jetpackcompose.booking.model.Tag
import dev.giussepr.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val hotel = Hotel(
                name = "Hotel California Strawberry",
                location = "Los Angeles, California",
                rate = 4.9,
                reviews = "14K",
                pricePerNight = 420.0,
                tags = listOf(
                    Tag("City-Center"),
                    Tag("Luxury"),
                    Tag("Instant Booking"),
                    Tag("Exclusive Deal"),
                    Tag("Early Bird Discount"),
                    Tag("Romantic Getaway"),
                    Tag("24/7 Support")
                ),
                description = "The advertisement features a vibrant and inviting design, showcasing the Hotel California Strawberry nestled in the heart of Los Angeles. Surrounded by the iconic Hollywood Sign, Griffith Park, and stunning beaches, the hotel is perfectly located for guests to explore L.A.’s best attractions.",
                offer = listOf(
                    Offer(
                        resId = R.drawable.bed,
                        name = "2 Bed"
                    ),
                    Offer(
                        resId = R.drawable.breakfast,
                        name = "Breakfast"
                    ),
                    Offer(
                        resId = R.drawable.cutlery,
                        name = "Kitchen"
                    ),
                    Offer(
                        resId = R.drawable.breakfast,
                        name = "Pet Friendly"
                    ),
                    Offer(
                        resId = R.drawable.serving_dish,
                        name = "Dinner"
                    ),
                    Offer(
                        resId = R.drawable.snowflake,
                        name = "Air Conditioning"
                    ),
                    Offer(
                        resId = R.drawable.television,
                        name = "TV"
                    ),
                    Offer(
                        resId = R.drawable.wi_fi_icon,
                        name = "wifi"
                    )
                )
            )

            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BookingScreen(
                        modifier = Modifier
                            .padding(innerPadding),
                        hotel = hotel
                    )
                }
            }
        }
    }
}