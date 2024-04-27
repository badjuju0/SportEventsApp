package com.example.sporteventsapp.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.AboutEvent
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.EventId
import com.example.sporteventsapp.data.EventTitle
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun AboutEventScreen(navController: NavController, dataStoreManager: DataStoreManager){

    lateinit var viewModel: MainViewModel

    val repository = Repository()

    val viewModelFactory = MainViewModelFactory(repository)

    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    val context = LocalLifecycleOwner.current

    var titleEvent by remember { mutableStateOf("") }
    var sportType by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var dates by remember { mutableStateOf("") }
    var organizer by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }

    LaunchedEffect(key1 = true ){
        dataStoreManager.getTitle().collect{title->
            viewModel.getEvent(title = title.title)
            titleEvent = title.title
        }

    }

    viewModel.myResponse.observe(context, Observer {response ->
        val event = response.body()!!
        sportType = event.sportType
        location = event.location
        dates = event.dates
        organizer = event.organizer
        phoneNumber = event.phoneNumber
        id = event.id


    })

    //val coroutine = rememberCoroutineScope()

//    coroutine.launch{
//        dataStoreManager.saveEventId(
//            eventIdData = EventId(id)
//        )
//    }

    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()){

        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
            ){
            Column {

                Text(text = titleEvent, fontSize = 24.sp, color = Color.Black)
                Text(text = "Вид спорта:${sportType}", fontSize = 16.sp, color = Color.Gray)
                Text(text = "Место проведения:${location}", fontSize = 16.sp, color = Color.Gray)
                Text(text = "Сроки проведения:${dates}", fontSize = 16.sp, color = Color.Gray)

                Text(text = "Организатор:${organizer}", fontSize = 24.sp, color = Color.Black, modifier = Modifier.offset(y = 90.dp))
                Text(text = "<ФИО организатора>", fontSize = 16.sp, color = Color.Black, modifier = Modifier.offset(y = 90.dp))
                Text(text = "<Контактный номер телефона:${phoneNumber}>", fontSize = 16.sp, color = Color.Black, modifier = Modifier.offset(y = 90.dp))
            }

        }



        Button(onClick = {
            navController.navigate(Screens.EventApplication.route)

        },

            modifier = Modifier
                .width(343.dp)
                .offset(y = 200.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            shape = RoundedCornerShape(100)
        ) {
            androidx.compose.material.Text(text = "Подать заявку", fontSize = 16.sp, color = Color.White)
        }

    }
}