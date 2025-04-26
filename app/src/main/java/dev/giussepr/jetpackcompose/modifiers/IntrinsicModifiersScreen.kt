package dev.giussepr.jetpackcompose.modifiers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.giussepr.jetpackcompose.ui.theme.JetpackComposeTheme
import dev.giussepr.jetpackcompose.utils.printConstraints

@Composable
fun IntrinsicModifiersScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .printConstraints("IntrinsicModifiersScreen: before width")
            // Here we set the width to IntrinsicSize.Max
            // to make the column take the max width of its children
            .width(IntrinsicSize.Max)
            .printConstraints("IntrinsicModifiersScreen: after width")
    ) {
        CheckBoxItem(text = "This is an option")
        CheckBoxItem(text = "This is an option with large text")
    }
}

@Composable
fun CheckBoxItem(modifier: Modifier = Modifier, text: String) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier,
            text = text
        )
        Checkbox(checked = false, onCheckedChange = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun IntrinsicModifiersScreenPreview() {
    JetpackComposeTheme {
        Scaffold {
            IntrinsicModifiersScreen(
                modifier = Modifier
                    .padding(it)
            )
        }
    }
}