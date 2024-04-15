package com.example.sporteventsapp.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.EventTitle
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import kotlinx.coroutines.launch


@SuppressLint("UnrememberedMutableState")
@Composable
fun EventScreen(navController: NavController, dataStoreManager: DataStoreManager){

    lateinit var viewModel: MainViewModel

    val repository = Repository()

    val viewModelFactory = MainViewModelFactory(repository)

    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    val context = LocalLifecycleOwner.current

    var titleList :List<EventTitle> by mutableStateOf(listOf())

    viewModel.getEvents()
    viewModel.myResponse3.observe(context, Observer {response ->
        titleList = response
    })


    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
        Text("Мероприятия", fontSize = 30.sp)

//        SearchBar()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            
            items(titleList) {text->
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


    Card(
        onClick = {navController.navigate(Screens.AboutEvent.route)
            coroutine.launch{
                dataStoreManager.saveTitle(
                    titleData = EventTitle(text.title)
                )
            }
                  },
        modifier = Modifier
            .width(343.dp)
            .height(77.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp)
    ){

            Text(text = text.title, fontSize = 16.sp, modifier = Modifier.padding(16.dp))



}}