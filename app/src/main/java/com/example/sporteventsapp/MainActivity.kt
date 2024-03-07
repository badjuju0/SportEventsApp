package com.example.sporteventsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.compose.BottomBar
import com.example.sporteventsapp.compose.EventScreen
import com.example.sporteventsapp.compose.NavGraph
import com.example.sporteventsapp.compose.RegistrationScreen
import com.example.sporteventsapp.compose.background
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.Post


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
            val loginPost = Post("Test","Test")
//            viewModel.getLogin(loginPost)

//            viewModel.myResponse.observe(this, Observer { response ->
//                if(response.isSuccessful){
//                    Log.d("Main", response.body().toString())
//                    Log.d("Main", response.code().toString())
//                    Log.d("Main", response.message())
//                } else{
//                    Toast.makeText(this,response.code(),Toast.LENGTH_SHORT).show()
//                }
//            })

            val navController = rememberNavController()
            Scaffold (bottomBar = { BottomBar(navController = navController)}){
                NavGraph(navController = navController)
            }


        }
    }
}







