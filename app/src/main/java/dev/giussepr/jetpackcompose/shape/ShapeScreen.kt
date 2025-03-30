package dev.giussepr.jetpackcompose.shape

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun ShapeScreen(modifier: Modifier = Modifier) {
    var numberOfPoints by remember { mutableIntStateOf(1) }
    var numberOfSteps by remember { mutableIntStateOf(1) }
    ShapeScreenContent(
        modifier = modifier,
        numberOfPoints = numberOfPoints,
        numberOfSteps = numberOfSteps,
        onNumberOfPointsChange = {
            numberOfPoints = it
        },
        onNumberOfStepsChange = {
            numberOfSteps = it
        })
}

@Composable
private fun ShapeScreenContent(
    modifier: Modifier = Modifier,
    numberOfPoints: Int,
    numberOfSteps: Int,
    onNumberOfPointsChange: (Int) -> Unit,
    onNumberOfStepsChange: (Int) -> Unit,
) {
    var starPath = Path()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = numberOfPoints.toString(),
                label = {
                    Text("Number of points")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    onNumberOfPointsChange(it.toIntOrNull() ?: 0)
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = numberOfSteps.toString(),
                label = {
                    Text("Number of steps")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    onNumberOfStepsChange(it.toIntOrNull() ?: 0)
                }
            )
        }
        if (numberOfPoints > 0) {
            Canvas(modifier = Modifier.size(300.dp)) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawRect(
                    color = Color.Blue,
                    size = Size(canvasWidth, canvasHeight),
                    style = Stroke(2f)
                )
                drawCircle(
                    color = Color.Blue,
                    radius = canvasWidth / 2,
                    style = Stroke(2f),
                )
                // Center point
                drawPoints(
                    points = listOf(
                        Offset(canvasWidth / 2, canvasHeight / 2),
                    ),
                    pointMode = PointMode.Points,
                    color = Color.Red,
                    strokeWidth = 20f,
                    cap = StrokeCap.Round
                )

                // Draw the numberOfPoints in the circle
                val angle = 360 / numberOfPoints
                val angles = mutableListOf<Int>()
                for (i in 0..<360 step angle) {
                    angles.add(i)
                }
                // The center of out circle is the middle of our canvas
                val circleX = canvasWidth / 2
                val circleY = canvasHeight / 2
                val radius = canvasWidth / 2
                val startPoints = mutableListOf<Offset>()
                angles.forEach { angle ->
                    // we need to convert the angle from degrees to radians
                    // Subtract 90 to start in superior part of the circle
                    val radians = Math.toRadians(angle.toDouble() - 90)
                    // Find x using trigonometry, x = r * cos(angle)
                    val x: Float = radius * cos(radians).toFloat()
                    // Fin y using trigonometry, y = r * sen(angle)
                    val y: Float = radius * sin(radians).toFloat()
                    val startPoint = Offset(x + circleX, y + circleY)
                    startPoints.add(startPoint)
                    drawPoints(
                        points = listOf(
                            startPoint
                        ),
                        pointMode = PointMode.Points,
                        color = Color.Red,
                        strokeWidth = 20f,
                        cap = StrokeCap.Round
                    )
                }
                // Draw the polygon
                val polygonPath = Path().apply {
                    moveTo(startPoints.first().x, startPoints.first().y)
                    for (i in 0 until startPoints.size) {
                        lineTo(startPoints[i].x, startPoints[i].y)
                    }
                    close()
                }
                drawPath(path = polygonPath, color = Color.Red, style = Stroke(2f))
                // Draw the star
                val starOrder =
                    List(startPoints.size) { i -> (i * numberOfSteps) % startPoints.size }
                starPath = Path().apply {
                    moveTo(startPoints[starOrder[0]].x, startPoints[starOrder[0]].y)
                    for (i in 1 until starOrder.size) {
                        lineTo(startPoints[starOrder[i]].x, startPoints[starOrder[i]].y)
                    }
                    close()
                }
                drawPath(starPath, color = Color.Blue, style = Stroke(width = 4f))
                /*val initialPoint = startPoints.first()
                var currentPoint = initialPoint
                do {
                    // Get the opposite point (pointIndex + 2)
                    val pointIndex = startPoints.indexOf(currentPoint)
                    val oppositePoint = startPoints[(pointIndex + numberOfSteps) % startPoints.size]
                    drawLine(
                        color = Color.Black,
                        start = currentPoint,
                        end = oppositePoint
                    )
                    currentPoint = oppositePoint
                } while (currentPoint != initialPoint)*/
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShapeScreenPreview() {
    MaterialTheme {
        ShapeScreenContent(
            numberOfPoints = 9,
            numberOfSteps = 5,
            onNumberOfPointsChange = {},
            onNumberOfStepsChange = {}
        )
    }
}