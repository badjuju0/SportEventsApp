package com.example.sporteventsapp.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Registration.route)
    {
        composable(route = Screens.Registration.route){

            RegistrationScreen(navController)
        }
        composable(route = Screens.Login.route){
            LoginScreen(navController)
        }
        composable(route = Screens.Event.route){
            EventScreen()
        }
        composable(route = Screens.Profile.route){
            ProfileScreen(navController)
        }
        composable(route = Screens.EventCreate.route){
            EventCreateScreen()
        }
    }
}