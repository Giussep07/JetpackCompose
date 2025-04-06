package dev.giussepr.jetpackcompose.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppScreens(
    val route: String,
    val title: String,
    val showToolbar: Boolean = true
) {
    @Serializable
    data object Home : AppScreens(route = "home", title = "Jetpack Compose")

    @Serializable
    data object BasicLayouts :
        AppScreens(route = "basic_layouts", title = "Basic Layouts")

    @Serializable
    data object StateManagement :
        AppScreens(route = "state_management", title = "State Management")

    @Serializable
    data object Shapes : AppScreens(route = "shapes", title = "Shapes")

    companion object {
        fun fromRoute(route: String): AppScreens {
            return when (route) {
                Home.route -> Home
                BasicLayouts.route -> BasicLayouts
                StateManagement.route -> StateManagement
                Shapes.route -> Shapes
                else -> throw IllegalArgumentException("Unknown route: $route")
            }
        }
    }
}