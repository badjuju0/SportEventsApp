package com.example.sporteventsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sporteventsapp.compose.BottomBar
import com.example.sporteventsapp.compose.EventScreen
import com.example.sporteventsapp.compose.NavGraph
import com.example.sporteventsapp.compose.RegistrationScreen



class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Scaffold (bottomBar = { BottomBar(navController = navController)}){
                NavGraph(navController = navController)
            }


        }
    }
}







