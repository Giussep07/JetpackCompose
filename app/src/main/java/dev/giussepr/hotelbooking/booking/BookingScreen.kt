@file:OptIn(ExperimentalLayoutApi::class)

package dev.giussepr.hotelbooking.booking

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.giussepr.hotelbooking.R
import dev.giussepr.hotelbooking.booking.model.Hotel
import dev.giussepr.hotelbooking.booking.model.Offer
import dev.giussepr.hotelbooking.booking.model.Tag

@Composable
fun BookingScreen(modifier: Modifier = Modifier, hotel: Hotel) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item { BannerSection(hotel = hotel) }
        item {
            BookingDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
            )
        }
        item {
            TagsSection(modifier = Modifier.fillMaxWidth(), tags = hotel.tags)
        }
        item {
            BookingDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp)
            )
        }
    }
}

@Composable
fun BannerSection(modifier: Modifier = Modifier, hotel: Hotel) {
    Box(
        modifier = modifier
            .heightIn(max = 250.dp)
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(R.drawable.living_room),
            contentDescription = "Hotel Living Room",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9).copy(alpha = 0.75f))
                .align(Alignment.BottomStart)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Hotel name
                Text(
                    text = hotel.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                // Hotel location
                LabeledIcon(
                    icon = {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Hotel location icon",
                            tint = Color.Black.copy(alpha = 0.45f)
                        )
                    },
                    label = hotel.location
                )
                // Hotel Rating
                LabeledIcon(
                    icon = {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Default.Star,
                            contentDescription = "Hotel location icon",
                            tint = Color(0xFFFCF300)
                        )
                    },
                    label = "${hotel.rate} (${hotel.reviews} reviews)"
                )
            }
            // Hotel price per night
            Text(
                modifier = Modifier
                    .align(Alignment.Bottom),
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("$${hotel.pricePerNight}/")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("night")
                    }
                }
            )
        }
    }
}

@Composable
private fun LabeledIcon(icon: @Composable () -> Unit, label: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BookingDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier
    )
}

@Composable
fun TagsSection(modifier: Modifier = Modifier, tags: List<Tag>) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.CenterHorizontally),
    ) {
        tags.forEach { tag ->
            AssistChip(
                onClick = {},
                label = {
                    Text(
                        text = tag.name
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun BookingScreenPreview() {
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
    MaterialTheme {
        BookingScreen(hotel = hotel)
    }
}