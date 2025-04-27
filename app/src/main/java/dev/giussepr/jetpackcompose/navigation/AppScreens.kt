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

    @Serializable
    data object FocusManagement :
        AppScreens(route = AppScreenRoutes.FOCUS_MANAGEMENT.route, title = "Focus Management")

    @Serializable
    data object IntrinsicModifiers :
        AppScreens(route = AppScreenRoutes.INTRINSIC_MODIFIERS.route, title = "Intrinsic Modifiers")

    @Serializable
    data object CustomLayout :
        AppScreens(route = AppScreenRoutes.CUSTOM_LAYOUT.route, title = "Custom Layout")

    companion object {
        fun fromRoute(route: String): AppScreens {
            return when (route) {
                Home.route -> Home
                BasicLayouts.route -> BasicLayouts
                StateManagement.route -> StateManagement
                Shapes.route -> Shapes
                ClickableModifiers.route -> ClickableModifiers
                FocusManagement.route -> FocusManagement
                IntrinsicModifiers.route -> IntrinsicModifiers
                CustomLayout.route -> CustomLayout
                else -> throw IllegalArgumentException("Unknown route: $route")
            }
        }
    }
}