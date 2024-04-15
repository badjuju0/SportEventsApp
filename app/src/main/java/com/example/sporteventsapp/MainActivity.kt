package com.example.sporteventsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.*
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.compose.BottomBar
import com.example.sporteventsapp.compose.EventScreen
import com.example.sporteventsapp.compose.NavGraph
import com.example.sporteventsapp.compose.RegistrationScreen
import com.example.sporteventsapp.compose.background
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.EventTitle
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.Post


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
    //val dataStoreManager = DataStoreManager(this)
        super.onCreate(savedInstanceState)
        setContent {

//            val repository = Repository()
//            val viewModelFactory = MainViewModelFactory(repository)
//            viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//            val loginPost = Post("pavlik22022003@gmail.com","123")
//            viewModel.getLogin(loginPost)
//                viewModel.myResponse1.observe(this, Observer { response ->
//                if(response.isSuccessful){
//                    Log.d("Response", response.body()?.firstName.toString())
//                    Log.d("Response", response.body()?.secondName.toString())
//                    Log.d("Response", response.message())
//                } else{
//                    Log.d("Response: ", response.errorBody().toString())
//                }
//            })



            val navController = rememberNavController()
            val dataStoreManager = DataStoreManager(this)
            Scaffold (bottomBar = { BottomBar(navController = navController)}){
                NavGraph(navController = navController, dataStoreManager = DataStoreManager(this))
            }


        }
    }
}







