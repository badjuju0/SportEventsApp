package com.example.sporteventsapp.compose

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.UrlRequest.Status
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.sporteventsapp.MainActivity
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.api.RetrofitInstance
import com.example.sporteventsapp.api.interceptor
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.Post
import com.example.sporteventsapp.data.PostNames
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.logging.HttpLoggingInterceptor
import kotlin.coroutines.coroutineContext


@Composable
fun LoginScreen(navController: NavController, dataStoreManager: DataStoreManager){

    lateinit var viewModel: MainViewModel

    val repository = Repository()

    val viewModelFactory = MainViewModelFactory(repository)

    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    val context =LocalLifecycleOwner.current

    val contextText = LocalContext.current

    interceptor.level = HttpLoggingInterceptor.Level.BODY

    Box (modifier = Modifier
        .height(90.dp)
        .fillMaxWidth()
        .background(color = lightColor),
        Alignment.TopCenter,
    ){
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Войти в аккаунт", fontSize = 30.sp, color = textColor)
            Text(text = "Множество соревнований ждут вас!", fontSize = 14.sp, color = textColor, modifier = Modifier.offset(y = 10.dp))
        }

    }


    Column (

        modifier = Modifier
            .fillMaxSize()
            .offset(y = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){




            var email_text by remember { mutableStateOf("") }
            var password_text by remember { mutableStateOf("") }

            val loginPost = Post(email_text,password_text)

        Box(modifier = Modifier
            .height(160.dp)
            .fillMaxWidth(),
        ){
            Column (modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                OutlinedTextField(
                    modifier = Modifier.padding(bottom = 15.dp).width(343.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = cardColor,
                        unfocusedBorderColor = cardColor),
                    value = email_text,
                    onValueChange = { email_text = it },
                    label = { Text("Email", fontSize = 14.sp, color = Color.Gray) })



                OutlinedTextField(
                    modifier = Modifier.padding(bottom = 15.dp).width(343.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = cardColor,
                        unfocusedBorderColor = cardColor),
                    value = password_text,
                    onValueChange = {password_text = it },
                    label = { Text("Пароль", fontSize = 14.sp, color = Color.Gray) })
            }
        }


            Button(onClick = {
                if(email_text.isEmpty() and  password_text.isEmpty()){
                    Toast.makeText(contextText, "Укажите все данные", Toast.LENGTH_SHORT).show()
                }
                if(email_text.isNotEmpty() and password_text.isNotEmpty()){
                    viewModel.getLogin(loginPost, dataStoreManager)
                    viewModel.myResponse1.observe(context, Observer {response ->
                        if(response.isSuccessful){
                            navController.navigate(Screens.Event.route)
                        }
                        else {
                            response.errorBody()?.toString()?.let { Log.d("ok", it) }
                        }
                    })
                }

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




