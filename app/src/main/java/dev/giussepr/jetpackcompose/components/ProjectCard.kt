package dev.giussepr.jetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProjectCard(modifier: Modifier = Modifier, title: String, description: String, date: String) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFE36850))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Check icon",
                tint = Color.White
            )
            Text(
                modifier = Modifier
                    .weight(1f),
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .rotate(90f),
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Move icon",
                tint = Color.White
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp),
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White.copy(alpha = 0.8f)
        )
        Text(
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 16.dp),
            text = date,
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(device = Devices.NEXUS_5)
@Composable
private fun ProjectCardPrev() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProjectCard(
                title = "Project X",
                description = "This is a short descripcion.",
                date = "Mar 5, 10:00"
            )
            ProjectCard(
                title = "Project X",
                description = "Bacon ipsum dolor amet pork chop bresaola pork loin, beef ribs meatball. Pork chop bresaola pork loin, beef ribs meatball.",
                date = "Mar 5, 10:00"
            )
        }
    }
}