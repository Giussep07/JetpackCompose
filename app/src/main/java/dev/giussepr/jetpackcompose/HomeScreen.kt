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
import dev.giussepr.jetpackcompose.navigation.AppScreenRoutes
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
        AppScreenRoutes.entries.filter { it.route != AppScreenRoutes.HOME.route }
            .forEach { appScreenRoute ->
                val appScreen = AppScreens.fromRoute(appScreenRoute.route)
                Button(onClick = { onScreenSelected(appScreen) }) {
                    Text(appScreen.title)
                }
            }
    }
}