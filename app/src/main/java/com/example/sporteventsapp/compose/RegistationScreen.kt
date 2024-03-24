package com.example.sporteventsapp.compose

import android.annotation.SuppressLint
import android.text.TextUtils
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
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.example.sporteventsapp.MainActivity
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.api.interceptor
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.PostReg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor


@SuppressLint("SuspiciousIndentation")
@Composable
fun RegistrationScreen(navController: NavController, dataStoreManager: DataStoreManager) {

    lateinit var viewModel: MainViewModel

    val repository = Repository()

    val viewModelFactory = MainViewModelFactory(repository)

    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    interceptor.level = HttpLoggingInterceptor.Level.BODY

        Column(modifier = Modifier
            .fillMaxSize()
            .offset(y = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

            Row {
                Text("Регистрация", fontSize = 30.sp, modifier = Modifier.
                offset(x= 30.dp))


                TextButton(onClick = {
                    navController.navigate(Screens.Login.route)
                }, modifier = Modifier.
                offset(x= 80.dp)) {
                    Text(text = "Войти", fontSize = 16.sp, color = buttonColor)
                }
            }
            var firstName_text by remember { mutableStateOf("") }
            var secondName_text by remember { mutableStateOf("") }
            var email_text by remember { mutableStateOf("") }
            var password_text by remember { mutableStateOf("") }

            val registerPost = PostReg(firstName_text, secondName_text, email_text, password_text)




            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = firstName_text,
                onValueChange = { firstName_text = it },
                label = { Text("Имя") })



            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = secondName_text,
                onValueChange = {secondName_text = it },
                label = { Text("Фамилия") })

            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = email_text,
                onValueChange = { email_text = it },
                label = { Text("Email") })

            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = password_text,
                onValueChange = { password_text = it },
                label = { Text("Создайте пароль") })

            Button(onClick = {
                navController.navigate(Screens.Event.route)
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.getRegister(registerPost, dataStoreManager)
                }
            },
                modifier = Modifier
                    .width(343.dp)
                    .offset(y = 60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                shape = RoundedCornerShape(100)
            ) {
                Text(text = "Зарегистрироваться", fontSize = 16.sp, color = Color.White)
            }
        }

    }


