package com.example.sporteventsapp.compose

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph (navController: NavHostController){
    NavHost(
        modifier = Modifier.background(color = background),
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
            EventScreen(navController)
        }
        composable(route = Screens.Profile.route){
            ProfileScreen(navController)
        }
        composable(route = Screens.EventCreate.route){
            EventCreateScreen()
        }
        composable(route = Screens.AboutEvent.route){
            AboutEventScreen(navController)
        }
        composable(route = Screens.EventApplication.route){
            EventApplicationScreen()
        }
    }
}