package com.example.sporteventsapp.compose

import android.content.Intent
import android.net.Uri
import android.net.http.UrlRequest.Status
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sporteventsapp.MainActivity
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.Post


@Composable
fun LoginScreen(navController: NavController){
    lateinit var viewModel: MainViewModel
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)


    Column (
        modifier = Modifier
            .fillMaxSize()
            .offset(y = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
            Text(text = "Войти в аккаунт", fontSize = 30.sp, color = Color.DarkGray)

            var email_text by remember { mutableStateOf("") }
            var password_text by remember { mutableStateOf("") }
            val loginPost = Post(email_text,password_text)
            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = email_text,
                onValueChange = { email_text = it },
                label = { Text("Email") })



            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = password_text,
                onValueChange = {password_text = it },
                label = { Text("Пароль") })




            Button(onClick = {
                navController.navigate(Screens.Event.route)
                viewModel.getLogin(loginPost)

            },
                modifier = Modifier
                    .width(343.dp)
                    .offset(y = 60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                shape = RoundedCornerShape(100)
            ) {
                Text(text = "Войти", fontSize = 16.sp, color = Color.White)
            }

    }
}




