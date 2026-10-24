package com.example.proyectofinal.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinal.View.AllView
import com.example.proyectofinal.View.HistoryView

import com.example.proyectofinal.View.HomeView
import com.example.proyectofinal.View.LoginView
import com.example.proyectofinal.View.MapView
import com.example.proyectofinal.View.PlusView
import com.example.proyectofinal.View.ProfileView
import com.example.proyectofinal.View.RegisterView
import com.google.firebase.auth.FirebaseAuth


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {


    val startRoute = if (FirebaseAuth.getInstance().currentUser != null) {
        Routes.Home.route
    } else {
        Routes.Login.route
    }


    NavHost(
        navController = navController,
        startDestination = startRoute,
        modifier = modifier
    ) {
        // DEFINIR DESTINOS ---------------------------------------------------
        composable(Routes.Login.route) {
            LoginView(
                onNavigateToRegister = { navController.navigate(Routes.Register.route) },
                onLoginSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Register.route) {
            RegisterView(
                onNavigateToLogin = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Register.route) { inclusive = true }
                    }
                },
                onRegisterSuccess = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Register.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home.route) {
            HomeView(
                onItemSelected = { index -> navigateByBar(navController, index) },
                onNavigateToPlus = { navController.navigate(Routes.Plus.route) },
                onNavigateToAll = { navController.navigate(Routes.All.route) }
            )
        }

        composable(Routes.Profile.route) {
            ProfileView(
                onItemSelected = { index -> navigateByBar(navController, index) },
                onLogout = {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.Home.route) { inclusive = true }
                    }
                }
            )
        }


        composable(Routes.History.route) {
            HistoryView(
                onItemSelected = { index -> navigateByBar(navController, index) }
            )
        }

        composable(Routes.Map.route) {
            MapView(
                onItemSelected = { index -> navigateByBar(navController, index) }
            )
        }
        // Pantalla del botón flotante (Plus / Cart)
        composable(Routes.Plus.route) {
            PlusView(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.All.route) {
            AllView(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

fun navigateByBar(navController: NavHostController, index: Int) {
    when (index) {
        0 -> navController.navigate(Routes.Home.route)
        1 -> navController.navigate(Routes.History.route)
        2 -> navController.navigate(Routes.Profile.route)
        3 -> navController.navigate(Routes.Map.route)
    }
}