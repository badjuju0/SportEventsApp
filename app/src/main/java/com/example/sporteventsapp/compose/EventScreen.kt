package com.example.sporteventsapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun EventScreen(navController: NavController){
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
        Text("Мероприятия", fontSize = 30.sp)

//        SearchBar()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
            items(2) {
                EventCard(modifier = Modifier, navController)

            }
        }

    }
    }


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventCard(modifier: Modifier, navController: NavController){
    Card(
        onClick = {navController.navigate(Screens.AboutEvent.route)},
        modifier = Modifier
            .width(343.dp)
            .height(77.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp)
    ){
        Column {
            Text("Соревнование", fontSize = 16.sp)
            Text("Открытое соревнование по спортивному туризму", fontSize = 14.sp)
        }}
}