package com.example.sporteventsapp.compose

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AboutEventScreen(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopStart
            ){
            Column {
                Text(text = "<EventName>", fontSize = 24.sp, color = Color.Black)
                Text(text = "<Информация>", fontSize = 16.sp, color = Color.Gray)
                Text(text = "<Место проведения", fontSize = 16.sp, color = Color.Gray)
                Text(text = "<Сроки проведения>", fontSize = 16.sp, color = Color.Gray)

                Text(text = "Организатор:", fontSize = 24.sp, color = Color.Black, modifier = Modifier.offset(y = 90.dp))
                Text(text = "<ФИО организатора>", fontSize = 16.sp, color = Color.Black, modifier = Modifier.offset(y = 90.dp))
                Text(text = "<номер телефона>", fontSize = 16.sp, color = Color.Black, modifier = Modifier.offset(y = 90.dp))
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