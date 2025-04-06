package dev.giussepr.jetpackcompose.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppScreens(
    val route: String,
    val title: String,
    val showToolbar: Boolean = true
) {
    @Serializable
    data object Home : AppScreens(route = AppScreenRoutes.HOME.route, title = "Jetpack Compose")

    @Serializable
    data object BasicLayouts :
        AppScreens(route = AppScreenRoutes.BASIC_LAYOUTS.route, title = "Basic Layouts")

    @Serializable
    data object StateManagement :
        AppScreens(route = AppScreenRoutes.STATE_MANAGEMENT.route, title = "State Management")

    @Serializable
    data object Shapes : AppScreens(route = AppScreenRoutes.SHAPES.route, title = "Shapes")

    @Serializable
    data object ClickableModifiers :
        AppScreens(route = AppScreenRoutes.CLICKABLE_MODIFIERS.route, title = "Clickable Modifiers")

    companion object {
        fun fromRoute(route: String): AppScreens {
            return when (route) {
                Home.route -> Home
                BasicLayouts.route -> BasicLayouts
                StateManagement.route -> StateManagement
                Shapes.route -> Shapes
                ClickableModifiers.route -> ClickableModifiers
                else -> throw IllegalArgumentException("Unknown route: $route")
            }
        }
    }
}