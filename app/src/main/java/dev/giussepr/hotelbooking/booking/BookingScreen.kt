package dev.giussepr.hotelbooking.booking

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookingScreen(modifier: Modifier = Modifier) {
    Text("Hello world!")
}

@Preview
@Composable
private fun BookingScreenPreview() {
    MaterialTheme {
        BookingScreen()
    }
}