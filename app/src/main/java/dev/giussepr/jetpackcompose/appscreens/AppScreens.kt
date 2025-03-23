package dev.giussepr.jetpackcompose.appscreens

sealed class AppScreens(
    val showToolbar: Boolean,
    val title: String
) {
    data object Home: AppScreens(showToolbar = true, title = "Jetpack Compose")
    data object BasicLayouts: AppScreens(showToolbar = true, title = "Basic Layouts")
    data object StateManagement: AppScreens(showToolbar = false, title = "State Management")
}