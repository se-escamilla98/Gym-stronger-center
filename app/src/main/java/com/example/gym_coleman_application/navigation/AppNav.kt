package com.example.gym_coleman_application.navigation


import android.net.Uri
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.runtime.Composable
import com.example.gym_coleman_application.ui.theme.login.LoginScreen
import com.example.gym_coleman_application.view.DrawerMenu
import com.example.gym_coleman_application.view.ProductoFormScreen

@Composable
fun AppNav(){
    // Crear navContoller para gestionar la navegacion

    val navController = rememberNavController()

    NavHost(navController=navController, startDestination = "login"){

        composable("login"){
            LoginScreen(navController = navController)
        }// fin composable 1

        composable(
            // route="muestraDatos/{username}",
            route="DrawerMenu/{username}",
            arguments = listOf(
                navArgument("username"){
                    type = NavType.StringType
                }
            )//fin listof

        ) // fin composable 2
        {// inicio back
                backStackEntry->
            val username = backStackEntry.arguments?.getString("username").orEmpty()
            //        MuestraDatosScreen(username=username, navController = navController)
            DrawerMenu(username=username, navController = navController)
        } // fin termino back

        // Enrutamiento para ProductoFormScreen

        composable(
            route="ProductoFormScreen/{nombre}/{precio}",
            arguments = listOf(
                navArgument("nombre"){ type = NavType.StringType },
                navArgument("precio"){ type = NavType.StringType },
            )//fin listof
        )// fin composable 3

        {// inicio back 2
                backStackEntry->
            val nombre = Uri.encode(backStackEntry.arguments?.getString("nombre") ?:"")

            val precio = backStackEntry.arguments?.getString("precio") ?:""


            ProductoFormScreen( navController = navController,  nombre=nombre, precio=precio)
        } // fin termino back 2

    }// fin NavHost
}// fin AppNav
