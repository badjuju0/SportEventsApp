package com.example.sporteventsapp.compose

import androidx.compose.foundation.Image
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        var firstName by remember { mutableStateOf("") }
        var secondName by remember { mutableStateOf("") }
        LaunchedEffect(key1 = true ){
            dataStoreManager.getData().collect{names->
                firstName = names.firstName
                secondName= names.secondName
            }
        }

        Text(text = "Профиль", fontSize = 30.sp, color = Color.Black)
        Image(painter = painterResource(id = R.drawable.icon), contentDescription = "icon", modifier = Modifier.clip(
            RoundedCornerShape(100.dp)
        ))
        Text(text = firstName, fontSize = 30.sp, color = Color.DarkGray, modifier = Modifier.offset(y = 50.dp))
        Text(text = secondName, fontSize = 30.sp, color = Color.DarkGray, modifier = Modifier.offset(y = 50.dp))
        Button(onClick = {
            navController.navigate(Screens.EventCreate.route)

        },
            modifier = Modifier
                .width(343.dp)
                .offset(y = 200.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            shape = RoundedCornerShape(100)
        ) {
            Text(text = "Создать мероприятие", fontSize = 16.sp, color = Color.White)
        }
    }
}