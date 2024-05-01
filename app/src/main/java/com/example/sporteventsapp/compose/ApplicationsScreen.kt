package com.example.sporteventsapp.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sporteventsapp.R
import com.example.sporteventsapp.api.Repository

import com.example.sporteventsapp.data.ApplicationDTO
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@SuppressLint("UnrememberedMutableState")
@Composable
fun ApplicationScreen(navController: NavController, dataStoreManager: DataStoreManager){

    val repository = Repository()

    val viewModel = viewModel<MainViewModel>(factory = MainViewModelFactory(repository))

    //lateinit var viewModel: MainViewModel
    //val viewModelFactory = MainViewModelFactory(repository)
    //viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)
    //val context = LocalLifecycleOwner.current
    //var applicationsList :List<ApplicationDTO> by mutableStateOf(listOf())
    val applicationsList by viewModel.applicationsList.collectAsState()
    var titleEvent by remember { mutableStateOf("") }



    LaunchedEffect(key1 = true ){
        dataStoreManager.getTitle().collect{title->
            titleEvent = title.title
            viewModel.getApplications(titleEvent)
        }
//        viewModel.myResponse4.observe(context, Observer { response ->
//            applicationsList = response
//        })
    }





    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Заявки", fontSize = 30.sp)


//        SearchBar()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(applicationsList) {application->
                ApplicationCard(modifier = Modifier, navController, application)

            }
        }

    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)

@Composable
fun ApplicationCard(modifier: Modifier, navController: NavController, application: ApplicationDTO){
    val coroutine = rememberCoroutineScope()


    Card(
        modifier = Modifier
            .width(345.dp)
            .height(130.dp)
            .padding(10.dp),
        backgroundColor = cardColor,
        shape = RoundedCornerShape(15.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                painter =
                painterResource(id = R.drawable.men1),
                contentDescription = "x",
                modifier = Modifier
                    .clickable(onClick = {})
                    .requiredSize(100.dp)
                    .padding(10.dp))

            Column ( modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(text = application.fio, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(5.dp))

                Text(text = "Возраст: ${application.age}", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(5.dp))

                Row( modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter =
                        painterResource(id = R.drawable.x),
                        contentDescription = "x",
                        modifier = Modifier
                            .clickable(onClick = {})
                            .requiredSize(70.dp)
                            .padding(bottom = 15.dp)
                    )
                    Image(
                        painter =
                        painterResource(id = R.drawable.accept),
                        contentDescription = "gal",
                        modifier = Modifier
                            .clickable(onClick = {})
                            .requiredSize(70.dp)
                            .padding(bottom = 15.dp)
                    )
                }
        }
        }




    }
}