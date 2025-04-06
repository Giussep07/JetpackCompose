package dev.giussepr.jetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.giussepr.jetpackcompose.HomeScreen
import dev.giussepr.jetpackcompose.booking.BookingScreen
import dev.giussepr.jetpackcompose.modifiers.ClickableModifiersScreen
import dev.giussepr.jetpackcompose.modifiers.FocusManagementScreen
import dev.giussepr.jetpackcompose.modifiers.ShapeScreen
import dev.giussepr.jetpackcompose.statemanagement.todolist.TodoListScreen

@Composable
fun AppNavGraph(modifier: Modifier, navController: NavHostController) {
    NavHost(modifier = modifier, navController = navController, startDestination = AppScreens.Home.route) {
        composable(AppScreens.Home.route) {
            HomeScreen(
                onScreenSelected = { appScreens ->
                    navController.navigate(route = appScreens.route)
                }
            )
        }

        composable(AppScreens.BasicLayouts.route) {
            BookingScreen()
        }

        composable(AppScreens.StateManagement.route) {
            TodoListScreen()
        }

        composable(AppScreens.Shapes.route) {
            ShapeScreen()
        }

        composable(AppScreens.ClickableModifiers.route) {
            ClickableModifiersScreen()
        }

        composable(AppScreens.FocusManagement.route) {
            FocusManagementScreen()
        }
    }
}