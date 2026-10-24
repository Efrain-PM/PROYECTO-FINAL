package com.example.proyectofinal.Navigation


sealed class Routes(val route:String) {
    object Login:Routes ("Login")
    object Register:Routes("Register")
    object Home:Routes("Home")
    object Plus:Routes("Plus")
    object Profile:Routes("Profile")
    object Map:Routes("Map")
    object All:Routes("Allview")
    object History:Routes("History")
}