package com.example.sporteventsapp.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box (contentAlignment = Alignment.TopCenter,
            modifier = Modifier
            .fillMaxWidth()
            .height(245.dp)
            .background(color = Color(0xFF0069FF))
        ){
            Text(text = "Профиль", fontSize = 30.sp, color = Color.White, modifier = Modifier.offset(y = 60.dp))
        }
        Text(text = "Имя Фамилия", fontSize = 30.sp, color = Color.DarkGray, modifier = Modifier.offset(y = 50.dp))
        Button(onClick = {
            navController.navigate(Screens.EventCreate.route)

        },
            modifier = Modifier
                .width(343.dp)
                .offset(y = 300.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0069FF)),
            shape = RoundedCornerShape(100)
        ) {
            Text(text = "Создать мероприятие", fontSize = 16.sp, color = Color.White)
        }
    }
}