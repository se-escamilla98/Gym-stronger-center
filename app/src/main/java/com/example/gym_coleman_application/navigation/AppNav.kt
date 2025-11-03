package com.example.gym_coleman_application.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gym_coleman_application.ui.theme.login.LoginScreen
import com.example.gym_coleman_application.view.DrawerMenu
import com.example.gym_coleman_application.view.ProductoFormScreen
import com.example.gym_coleman_application.view.TopBar
import com.example.gym_coleman_application.view.WelcomeScreen
import kotlinx.coroutines.launch

@Composable
fun AppNav() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val gesturesEnabled = currentRoute != "login"

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = gesturesEnabled,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ) {
                val username = navBackStackEntry?.arguments?.getString("username").orEmpty()
                DrawerMenu(username = username, navController = navController)
            }
        }
    ) {
        Scaffold(
            topBar = {
                if (gesturesEnabled) {
                    TopBar(onMenuClick = { scope.launch { drawerState.open() } })
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "login",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("login") {
                    LoginScreen(navController = navController)
                }
                composable(
                    route = "home/{username}",
                    arguments = listOf(navArgument("username") { type = NavType.StringType })
                ) { backStackEntry ->
                    val username = backStackEntry.arguments?.getString("username").orEmpty()
                    WelcomeScreen(username = username)
                }
                composable(
                    route = "ProductoFormScreen/{nombre}/{precio}",
                    arguments = listOf(
                        navArgument("nombre") { type = NavType.StringType },
                        navArgument("precio") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
                    val precio = backStackEntry.arguments?.getString("precio") ?: ""
                    ProductoFormScreen(navController = navController, nombre = nombre, precio = precio)
                }
            }
        }
    }
}
