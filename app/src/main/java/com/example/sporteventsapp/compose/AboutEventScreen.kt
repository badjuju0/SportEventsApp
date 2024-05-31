package com.example.sporteventsapp.compose

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sporteventsapp.R
import com.example.sporteventsapp.api.Repository
import com.example.sporteventsapp.data.AboutEvent
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.EventId
import com.example.sporteventsapp.data.EventTitle
import com.example.sporteventsapp.data.MainViewModel
import com.example.sporteventsapp.data.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun AboutEventScreen(navController: NavController, dataStoreManager: DataStoreManager){

    val repository = Repository()

    val viewModel = viewModel<MainViewModel>(factory = MainViewModelFactory(repository))

    val event by viewModel.event.collectAsState()

//    lateinit var viewModel: MainViewModel
//
//    val repository = Repository()
//
//    val viewModelFactory = MainViewModelFactory(repository)
//
//    viewModel = ViewModelProvider(ViewModelStore(), viewModelFactory).get(MainViewModel::class.java)

    val context = LocalLifecycleOwner.current

    var titleEvent by remember { mutableStateOf("") }
//    var sportType by remember { mutableStateOf("") }
//    var location by remember { mutableStateOf("") }
//    var dates by remember { mutableStateOf("") }
//    var organizer by remember { mutableStateOf("") }
//    var phoneNumber by remember { mutableStateOf("") }
//    var id by remember { mutableStateOf("") }
//    var owner by remember { mutableStateOf("") }

    var userEmail by remember { mutableStateOf("") }

    var isVisible by remember { mutableStateOf(false) }

    val imageList = listOf(
        R.drawable.boxback,
        R.drawable.tenisback,
        R.drawable.footback,
        R.drawable.basketback,
        R.drawable.runback,
        R.drawable.hockeyback,
        R.drawable.jymback,
        R.drawable.athleticback,
        R.drawable.sportback,
    )
    LaunchedEffect(key1 = true ){
        dataStoreManager.getTitle().collect{title->
            viewModel.getEvent(title = title.title)
            titleEvent = title.title
        }


    }

    LaunchedEffect(key1 = true ){
        dataStoreManager.getEmail().collect{email->
            userEmail = email
        }
    }
    if (event?.owner == userEmail) {
        isVisible = true
    }
//    viewModel.myResponse.observe(context, Observer {response ->
//        val event = response.body()!!
//        sportType = event.sportType
//        location = event.location
//        dates = event.dates
//        organizer = event.organizer
//        phoneNumber = event.phoneNumber
//        id = event.id
//        owner = event.owner
//
//
//    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
    ){


        Box (modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = lightColor),
            Alignment.Center,
        ){
            androidx.compose.material.Text("Подробнее", fontSize = 30.sp, color = textColor)
        }

        Box(modifier = Modifier
            .height(200.dp)
            .width(350.dp)
            .offset(y = 30.dp),
            contentAlignment = Alignment.TopCenter
            ){



            Image(
                painter = when (event?.sportType) {
                    "Бокс" -> painterResource(id = R.drawable.boxback)
                    "Бег" -> painterResource(id = R.drawable.runback)
                    "Атлектика" -> painterResource(id = R.drawable.athleticback)
                    "Тяжелая атлектика" -> painterResource(id = R.drawable.jymback)
                    "Футбол" -> painterResource(id = R.drawable.footback)
                    "Хоккей" -> painterResource(id = R.drawable.hockeyback)
                    "Баскетбол" -> painterResource(id = R.drawable.basketback)
                    else -> { // Note the block
                        painterResource(id = R.drawable.sportback)
                    }
                },
                contentDescription = "background",
                modifier = Modifier
                    .blur(
                        radiusX = 5.dp,
                        radiusY = 5.dp,
                        edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                    ),
                contentScale = ContentScale.FillBounds
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = titleEvent, fontSize = 24.sp,fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = "Вид спорта:${event?.sportType}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)

                Text(text = "Сроки проведения:${event?.dates}", fontSize = 20.sp,fontWeight = FontWeight.Bold, color = Color.White)

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 145.dp),
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {

                if (isVisible){
                    Button(
                        onClick = {
                            navController.navigate(Screens.Applications.route)
                        },
                        modifier = Modifier
                            .width(150.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = lightColor),
                        shape = RoundedCornerShape(100)
                    ) {
                        androidx.compose.material.Text(
                            text = "Рассмотреть заявки",
                            fontSize = 16.sp,
                            color = textColor
                        )
                    }
                    Button(
                        onClick = {
                            navController.navigate(Screens.Participants.route)
                        },
                        modifier = Modifier
                            .width(145.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = lightColor),
                        shape = RoundedCornerShape(100)
                    ) {
                        androidx.compose.material.Text(text = "Список участников", fontSize = 16.sp, color = textColor)
                    }
                }





            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 100.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "background",
                modifier = Modifier.requiredSize(120.dp)
            )
            Column (modifier = Modifier
                .padding(end = 40.dp)
                .offset(y = 20.dp), horizontalAlignment = Alignment.End){
                Text(text = "Место проведения:", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = textColor)
                event?.location?.let {
                    Text(textAlign = TextAlign.End, text = it, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor,
                        modifier = Modifier
                            .background(color = lightColor)
                            .fillMaxWidth())
                }
            }

        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 150.dp)
                .padding(end = 100.dp)
        ){
            Column (modifier = Modifier.padding(start = 20.dp), horizontalAlignment = Alignment.Start){
                Text(text = "Организатор:", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = textColor)
                event?.organizer?.let {
                    Text(textAlign = TextAlign.Start, text = it, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = textColor,
                        modifier = Modifier
                            .background(color = lightColor)
                            .width(450.dp))
                }
                Row (
                    horizontalArrangement = Arrangement.Start
                ){
                    Image(painter = painterResource(id = R.drawable.phone), contentDescription = "phone",
                        modifier = Modifier
                            .requiredSize(30.dp)

                    )
                    event?.phoneNumber?.let {
                        Text(text = it, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textColor,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }

            }
            Image(painter = painterResource(id = R.drawable.icon),
                contentDescription = "icon",
                modifier = Modifier
                    .border(
                        BorderStroke(20.dp, lightColor),
                        CircleShape
                    )
                    .requiredSize(120.dp)
                    .padding(20.dp)
                    .clip(
                        CircleShape
                    ))


        }


        Button(onClick = {
            navController.navigate(Screens.EventApplication.route)

        },
            modifier = Modifier
                .width(343.dp)
                .offset(y = 160.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = textColor),
            shape = RoundedCornerShape(100)
        ) {
            androidx.compose.material.Text(text = "Подать заявку", fontSize = 16.sp, color = Color.White)
        }

    }
}