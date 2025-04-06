package dev.giussepr.jetpackcompose.modifiers

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.giussepr.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun ClickableModifiersScreen(modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }

    val isPressed by interactionSource.collectIsPressedAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = modifier
                .size(100.dp)
                .background(if (isPressed) Color.Red else Color.Blue)
                .clickable(
                    interactionSource = interactionSource,
                    LocalIndication.current,
                    onClick = { println("Clicked!") }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("Hello world!")
        }
    }
}

@Preview
@Composable
private fun ClickableModifiersScreenPreview() {
    JetpackComposeTheme {
        ClickableModifiersScreen()
    }
}