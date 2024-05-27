package com.example.sporteventsapp.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sporteventsapp.R
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.EventTitle
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun EventScreen(navController: NavController, dataStoreManager: DataStoreManager){

    val repository = Repository()

    val viewModel = viewModel<MainViewModel>(factory = MainViewModelFactory(repository))

    val eventsList by viewModel.eventsList.collectAsState()

//    lateinit var viewModel: MainViewModel
//
//    val repository = Repository()
//
//    val viewModelFactory = MainViewModelFactory(repository)
//
//    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    val context = LocalLifecycleOwner.current

    var titleList :List<EventTitle> by mutableStateOf(listOf())

    viewModel.getEvents()
//    viewModel.myResponse3.observe(context, Observer {response ->
//        titleList = response
//    })


    Box (modifier = Modifier
        .height(90.dp)
        .fillMaxWidth()
        .background(color = lightColor),
        Alignment.TopCenter,
    )
    {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {

            Text("Мероприятия", fontSize = 30.sp, color = textColor)
            Text("Нажмите  на интересующее вас событие", fontSize = 14.sp, color = textColor, modifier = Modifier.padding(10.dp))
        }

    }
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(top = 85.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

        //SearchBar()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            
            items(eventsList) {text->
                EventCard(modifier = Modifier, navController, text, dataStoreManager)

            }
        }

    }
    }


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventCard(modifier: Modifier, navController: NavController, text: EventTitle,dataStoreManager: DataStoreManager ){
    val coroutine = rememberCoroutineScope()

    val imageList = listOf(
        R.drawable.badminton,
        R.drawable.basketball,
        R.drawable.boxing,
        R.drawable.football,
        R.drawable.run,
        R.drawable.tennis,
        R.drawable.stopwatch,
        R.drawable.gymming,
        R.drawable.trophy,
        R.drawable.table,
        R.drawable.bottle
    )

    val randomImage = imageList.random()

    Card(
        onClick = {navController.navigate(Screens.AboutEvent.route)
            coroutine.launch{
                dataStoreManager.saveTitle(
                    titleData = EventTitle(text.title)
                )
            } },
        modifier = Modifier
            .width(343.dp)
            .height(84.dp)
            .padding(10.dp),
        backgroundColor = cardColor,
        shape = RoundedCornerShape(15.dp)
    ){
        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter =
                painterResource(id = randomImage),
                contentDescription = "type",
                modifier = Modifier
                    .clickable(onClick = {})
                    .requiredSize(100.dp)
                    .padding(10.dp))

            Text(text = text.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(16.dp))

            Image(painter =
            painterResource(id = R.drawable.mark),
                contentDescription = "mark",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .padding(5.dp)
            )
        }




    }}
