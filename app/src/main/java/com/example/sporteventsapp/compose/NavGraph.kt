package com.example.sporteventsapp.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.EventTitle
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.TokenPost
import okhttp3.internal.wait


@Composable
fun NavGraph (navController: NavHostController, dataStoreManager: DataStoreManager){

    val repository = Repository()

    val viewModel = viewModel<MainViewModel>(factory = MainViewModelFactory(repository))

    val token by viewModel.tokenResponse.collectAsState()

    val context = LocalLifecycleOwner.current

    LaunchedEffect(key1 = true ){
        dataStoreManager.getData().collect{names->
            viewModel.getToken(TokenPost(names.token) )

        }
    }


    NavHost(
        modifier = Modifier.background(color = background),
        navController = navController,

        startDestination =  if(token?.answer == "success"){
            Screens.Event.route
        } else {
            Screens.Registration.route
        })
    {
        composable(route = Screens.Registration.route){

            RegistrationScreen(navController,dataStoreManager)
        }
        composable(route = Screens.Login.route){
            LoginScreen(navController, dataStoreManager)
        }
        composable(route = Screens.Event.route){
            EventScreen(navController, dataStoreManager)
        }
        composable(route = Screens.Profile.route){
            ProfileScreen(navController, dataStoreManager)
        }
        composable(route = Screens.EventCreate.route){
            EventCreateScreen(navController, dataStoreManager)
        }
        composable(route = Screens.AboutEvent.route){
            val text: EventTitle
            AboutEventScreen(navController,dataStoreManager )
        }
        composable(route = Screens.EventApplication.route){
            EventApplicationScreen(dataStoreManager)
        }
        composable(route = Screens.Applications.route){
            ApplicationScreen(navController, dataStoreManager)
        }
        composable(route = Screens.Participants.route){
            ParticipantsScreen(navController, dataStoreManager)
        }
    }
}