package com.example.sporteventsapp.compose

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sporteventsapp.R
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.ApplicationDTO
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory

@SuppressLint("UnrememberedMutableState")
@Composable
fun ParticipantsScreen(navController: NavController, dataStoreManager: DataStoreManager){

    val repository = Repository()

    val viewModel = viewModel<MainViewModel>(factory = MainViewModelFactory(repository))

    val participantsList by viewModel.participantsList.collectAsState()

    var titleEvent by remember { mutableStateOf("") }

    LaunchedEffect(key1 = true ){
        dataStoreManager.getTitle().collect{title->
            titleEvent = title.title
            viewModel.getParticipants(titleEvent)
        }
    }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Участники", fontSize = 30.sp)


//        SearchBar()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(participantsList) {participant->
                ParticipantCard(modifier = Modifier, participant)


            }
        }

    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)

@Composable
fun ParticipantCard(modifier: Modifier, participant: ApplicationDTO){
    val coroutine = rememberCoroutineScope()

    val imageList = listOf(
        R.drawable.men1,
        R.drawable.men2,
        R.drawable.men3,
        R.drawable.men4,
        R.drawable.men5,
        R.drawable.men6,
    )

    val randomImage = imageList.random()
    Card(
        modifier = Modifier
            .width(345.dp)
            .height(130.dp)
            .padding(10.dp),
        backgroundColor = cardColor,
        shape = RoundedCornerShape(15.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                painter =
                painterResource(id = randomImage),
                contentDescription = "men",
                modifier = Modifier
                    .clickable(onClick = {})
                    .requiredSize(100.dp)
                    .padding(10.dp))

            Column ( modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            )
            {
                Text(text = participant.fio, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(5.dp))

                Text(text = "Возраст: ${participant.age}", fontWeight = FontWeight.Bold, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(5.dp))

            }
        }




    }
}