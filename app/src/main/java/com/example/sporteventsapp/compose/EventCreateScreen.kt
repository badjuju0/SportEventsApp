package com.example.sporteventsapp.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.api.interceptor
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.PostEvents
import okhttp3.logging.HttpLoggingInterceptor

@Composable
fun EventCreateScreen(navController: NavController, dataStoreManager:DataStoreManager){

    lateinit var viewModel: MainViewModel

    val repository = Repository()

    val viewModelFactory = MainViewModelFactory(repository)

    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    interceptor.level = HttpLoggingInterceptor.Level.BODY

    Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Укажите необходимые данные", fontSize = 20.sp, color = Color.Black)

        var sportType by remember { mutableStateOf("") }
        var title by remember { mutableStateOf("") }
        var dates by remember { mutableStateOf("") }
        var location by remember { mutableStateOf("") }
        var organizer by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var owner by remember { mutableStateOf("") }

        LaunchedEffect(key1 = true ){
            dataStoreManager.getEmail().collect{email->
                owner = email

            }
        }
        val eventPost  = PostEvents(sportType,title,dates,location,organizer,phoneNumber, owner)

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = sportType,
            onValueChange = { sportType = it },
            label = { Text("Выберите вид спорта", fontSize = 14.sp, color = Color.Gray) })

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = title,
            onValueChange = { title = it },
            label = { Text("Название", fontSize = 14.sp, color = Color.Gray) })

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = dates,
            onValueChange = { dates = it },
            label = { Text("Сроки проведения", fontSize = 14.sp, color = Color.Gray) })

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = location,
            onValueChange = { location = it },
            label = { Text("Место проведения", fontSize = 14.sp, color = Color.Gray) })

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = organizer,
            onValueChange = { organizer = it },
            label = { Text("Организаторы", fontSize = 14.sp, color = Color.Gray) })

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Контактный телефон", fontSize = 14.sp, color = Color.Gray) })

        Button(onClick = {
            viewModel.createEvent(eventPost)
            navController.navigate(Screens.Profile.route)
        },
            modifier = Modifier
                .width(343.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            shape = RoundedCornerShape(100)
        ) {
            Text(text = "Дальше", fontSize = 16.sp, color = Color.White)
        }
    }
}