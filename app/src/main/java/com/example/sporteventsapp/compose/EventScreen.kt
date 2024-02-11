package com.example.sporteventsapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventScreen(){
    Text("Мероприятия", fontSize = 30.sp, modifier = Modifier.
    offset(x= 70.dp))
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

        item(5) {
            Card(
                modifier = Modifier
                    .width(343.dp)
                    .height(77.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(15.dp)
            ){
                Column {
                    Text("Соревнование", fontSize = 16.sp)
                    Text("Открытое соревнование по спортивному туризму", fontSize = 14.sp)
                }

            }
        }
    }
}
