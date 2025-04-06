@file:OptIn(ExperimentalMaterial3Api::class)

package dev.giussepr.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.giussepr.jetpackcompose.appscreens.AppScreens
import dev.giussepr.jetpackcompose.booking.BookingScreen
import dev.giussepr.jetpackcompose.booking.model.Hotel
import dev.giussepr.jetpackcompose.booking.model.Offer
import dev.giussepr.jetpackcompose.booking.model.Tag
import dev.giussepr.jetpackcompose.modifiers.ShapeScreen
import dev.giussepr.jetpackcompose.statemanagement.todolist.TodoListScreen
import dev.giussepr.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                MainActivityContent()
            }
        }
    }

    @Composable
    fun MainActivityContent() {
        var currentScreen: AppScreens by remember { mutableStateOf(AppScreens.Home) }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentScreen.title) },
                    navigationIcon = {
                        if (currentScreen != AppScreens.Home && currentScreen.showToolbar) {
                            IconButton(onClick = {
                                currentScreen = AppScreens.Home
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            when (currentScreen) {
                AppScreens.Home -> MainMenu(
                    Modifier.padding(innerPadding),
                    onScreenSelected = { currentScreen = it })

                AppScreens.BasicLayouts -> BasicLayouts(modifier = Modifier.fillMaxSize())
                AppScreens.StateManagement -> StateManagement(
                    modifier = Modifier.padding(
                        innerPadding
                    )
                )
                AppScreens.Shapes -> ShapeScreen(
                    modifier = Modifier
                        .padding(innerPadding)
                )
            }
        }
        BackHandler {
            currentScreen = AppScreens.Home
        }
    }

    @Composable
    fun MainMenu(modifier: Modifier = Modifier, onScreenSelected: (AppScreens) -> Unit) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = "Jetpack Compose Learning App",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Button(onClick = { onScreenSelected(AppScreens.BasicLayouts) }) {
                Text("Basic Layouts")
            }
            Button(onClick = { onScreenSelected(AppScreens.StateManagement) }) {
                Text("State Management")
            }
            Button(onClick = { onScreenSelected(AppScreens.Shapes) }) {
                Text("Shapes")
            }
        }
    }

    @Composable
    private fun BasicLayouts(modifier: Modifier) {
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

        BookingScreen(
            modifier = modifier,
            hotel = hotel
        )
    }

    @Composable
    fun StateManagement(modifier: Modifier = Modifier) {
        TodoListScreen(modifier = modifier)
    }

    @Preview
    @Composable
    private fun MainActivityContentPreview() {
        JetpackComposeTheme {
            MainActivityContent()
        }
    }
}