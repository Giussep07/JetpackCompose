package dev.giussepr.jetpackcompose.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.giussepr.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlin.random.Random

@Composable
fun CustomSubComposeLayoutScreen(modifier: Modifier = Modifier) {
    var page by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SubComposePagedRow(
            page = page
        ) {
            (1..1000).forEach {
                Box(
                    modifier = Modifier
                        .width(Random.nextInt(10, 300).dp)
                        .height(Random.nextInt(10, 300).dp)
                        .background(Color(Random.nextInt()))
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    if (page > 0) {
                        page--
                    }
                }) {
                Text("Previous")
            }
            Button(
                modifier = Modifier
                    .weight(1f),
                onClick = {
                    page++
                }
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomLayoutScreenPreview() {
    JetpackComposeTheme {
        CustomSubComposeLayoutScreen(
            modifier = Modifier
                .statusBarsPadding()
        )
    }
}