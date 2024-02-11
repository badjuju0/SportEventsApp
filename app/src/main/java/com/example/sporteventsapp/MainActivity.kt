package com.example.sporteventsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sporteventsapp.compose.EventScreen
import com.example.sporteventsapp.compose.RegistrationScreen
import com.example.sporteventsapp.compose.mUserViewModel
import com.example.sporteventsapp.data.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "RegistrationScreen" ){
                composable("RegistrationScreen"){
                    RegistrationScreen{
                        navController.navigate("EventScreen")
                    }
                }
                composable("EventScreen"){
                    EventScreen()
                }

            }
        }
    }
}







