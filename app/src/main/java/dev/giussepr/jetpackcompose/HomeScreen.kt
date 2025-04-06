package dev.giussepr.jetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.giussepr.jetpackcompose.navigation.AppScreens

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onScreenSelected: (AppScreens) -> Unit) {
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