package com.example.sporteventsapp.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sporteventsapp.R
import com.example.sporteventsapp.data.DataStoreManager

@Composable
fun ProfileScreen(navController: NavController, dataStoreManager: DataStoreManager){
    Box(modifier =
    Modifier
        .height(325.dp)
        .fillMaxWidth())
    {
        Image(painter = painterResource(id = R.drawable.bg),
            contentDescription ="bg",
            modifier = Modifier
                .fillMaxWidth())
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        var firstName by remember { mutableStateOf("") }
        var secondName by remember { mutableStateOf("") }
        LaunchedEffect(key1 = true ){
            dataStoreManager.getData().collect{names->
                firstName = names.firstName
                secondName= names.secondName
            }
        }
        Row (modifier = Modifier.fillMaxWidth().offset(x= 150.dp)){
            Text(text = "Профиль", fontSize = 30.sp, color = textColor)
            TextButton(modifier = Modifier.padding(start = 55.dp), onClick = {
                navController.navigate(Screens.Login.route)
            }) {
                Text(text = "Выйти", fontSize = 16.sp, color = textColor)
            }
        }

        Image(painter = painterResource(id = R.drawable.icon),
            contentDescription = "icon",
            modifier = Modifier
                .offset(y = 100.dp)
                .border(
                    BorderStroke(8.dp, Color.White),
                    CircleShape
                )
                .padding(bottom = 100.dp)
                .requiredSize(280.dp)
                .clip(
                    CircleShape
                ))
        Text(text = firstName, fontSize = 30.sp, color = textColor, modifier = Modifier.offset(y = 50.dp))
        Text(text = secondName, fontSize = 30.sp, color = textColor, modifier = Modifier.offset(y = 50.dp))
        Button(onClick = {
            navController.navigate(Screens.EventCreate.route)

        },
            modifier = Modifier
                .width(343.dp)
                .offset(y = 100.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            shape = RoundedCornerShape(100)
        ) {
            Text(text = "Создать мероприятие", fontSize = 16.sp, color = Color.White)
        }
    }
}