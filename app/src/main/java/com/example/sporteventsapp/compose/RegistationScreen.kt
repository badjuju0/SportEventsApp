package com.example.sporteventsapp.compose

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
import com.example.sporteventsapp.MainActivity
import com.example.sporteventsapp.data.User
import com.example.sporteventsapp.data.UserViewModel


@Composable
fun RegistrationScreen(onClick:()-> Unit) {
        Column(modifier = Modifier
            .fillMaxSize()
            .offset(y = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally){

            Row {
                Text("Регистрация", fontSize = 30.sp, modifier = Modifier.
                offset(x= 30.dp))


                TextButton(onClick = {}, modifier = Modifier.
                offset(x= 80.dp)) {
                    Text(text = "Войти", fontSize = 16.sp, color = Color(0xFF0069FF))
                }
            }
            var firstName_text by remember { mutableStateOf("") }
            var lastName_text by remember { mutableStateOf("") }
            var email_text by remember { mutableStateOf("") }
            var password_text by remember { mutableStateOf("") }

            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = firstName_text,
                onValueChange = { firstName_text = it },
                label = { Text("Имя") })



            OutlinedTextField(
                modifier = Modifier.width(343.dp),
                value = lastName_text,
                onValueChange = {lastName_text = it },
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
                insertDataToDatabase(firstName_text, lastName_text,email_text, password_text)
                onClick()


            },
                modifier = Modifier
                    .width(343.dp)
                    .offset(y = 60.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0069FF)),
                shape = RoundedCornerShape(100)
            ) {
                Text(text = "Зарегистрироваться", fontSize = 16.sp, color = Color.White)
            }
        }

    }
    lateinit var mUserViewModel: UserViewModel

    fun insertDataToDatabase(firstName_text: String,lastName_text: String,emai_text: String,password_text: String) {
        val firstName = firstName_text.toString()
        val lastName = lastName_text.toString()
        val email = emai_text.toString()
        val password = password_text.toString()

        if (inputCheck(firstName, lastName, email, password)){
            val user = User(0, firstName, lastName, email, password, organizer = false)
            mUserViewModel.addUser(user)
            print("Succes")
        }
    }

    fun inputCheck(firstName:String, lastName:String, email:String, password:String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }


