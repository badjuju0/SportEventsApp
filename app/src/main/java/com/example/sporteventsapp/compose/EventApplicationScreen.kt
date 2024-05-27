package com.example.sporteventsapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import com.example.sporteventsapp.data.PostApplication

@Composable
fun EventApplicationScreen(dataStoreManager: DataStoreManager){

    lateinit var viewModel: MainViewModel

    val repository = Repository()

    val viewModelFactory = MainViewModelFactory(repository)

    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Подача заявки", fontSize = 20.sp)
        //RadioButtons()
        var teamName by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var age by remember { mutableStateOf("") }
        var fio by remember { mutableStateOf("") }
        var eventTitle by remember { mutableStateOf("") }
        var approve by remember { mutableStateOf(null) }
        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = teamName,
            onValueChange = { teamName = it },
            label = { Text("Название команды", fontSize = 14.sp, color = Color.Gray) })


        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = fio,
            onValueChange = { fio = it },
            label = { Text("ФИО участника", fontSize = 14.sp, color = Color.Gray) })

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = age,
            onValueChange = { age = it },
            label = { Text("Возраст участника", fontSize = 14.sp, color = Color.Gray) })

        OutlinedTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(343.dp),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = cardColor,
                unfocusedBorderColor = cardColor),
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Контактный телефон", fontSize = 14.sp, color = Color.Gray) })

        LaunchedEffect(key1 = true ){
            dataStoreManager.getTitle().collect{title->
                eventTitle = title.title
            }

        }

        val post = PostApplication(fio,age,phoneNumber,teamName, approve, eventTitle)

        Button(onClick = {
            viewModel.createApplication(post)

        },

            modifier = Modifier
                .width(343.dp)
                .offset(y = 200.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
            shape = RoundedCornerShape(100)
        ) {
            Text(text = "Отправить", fontSize = 16.sp, color = Color.White)
        }

    }
}


@Composable
fun RadioButtons() {
    val selectedValue = remember { mutableStateOf("") }

    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    val items = listOf("От команды (клуба)", "Лично")
    Column(Modifier.padding(8.dp)) {
        //Text(text = "Selected value: ${selectedValue.value.ifEmpty { "NONE" }}")
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(item),
                        onClick = { onChangeState(item) }
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(item),
                    onClick = null
                )
                Text(
                    text = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}