@file:OptIn(ExperimentalMaterial3Api::class)

package dev.giussepr.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.giussepr.jetpackcompose.navigation.AppNavGraph
import dev.giussepr.jetpackcompose.navigation.AppScreens
import dev.giussepr.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                MainActivityContent()
            }
        }
    }

    @Composable
    fun MainActivityContent() {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentScreen =
            AppScreens.fromRoute(navBackStackEntry?.destination?.route ?: AppScreens.Home.route)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentScreen.title) },
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null && currentScreen.showToolbar) {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            AppNavGraph(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
        BackHandler(enabled = navController.previousBackStackEntry != null) {
            navController.popBackStack()
        }
    }

    @Preview
    @Composable
    private fun MainActivityContentPreview() {
        JetpackComposeTheme {
            MainActivityContent()
        }
    }
}