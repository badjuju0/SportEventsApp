package com.example.sporteventsapp.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sporteventsapp.api.Repository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {


    var myResponse: MutableLiveData<Answer> = MutableLiveData()

    val myResponse1: MutableLiveData<Response<PostNames>> = MutableLiveData()

    var myResponse2: List<EventTitle> by mutableStateOf(listOf())

    var myResponse3: MutableLiveData<List<EventTitle>> = MutableLiveData()

    val eventsList = MutableStateFlow(emptyList<EventTitle>())

    val event = MutableStateFlow<AboutEvent?>(null)

    var tokenResponse = MutableStateFlow<Answer?>(null)

    var myResponse4: MutableLiveData<List<ApplicationDTO>> = MutableLiveData()

    val applicationsList = MutableStateFlow(emptyList<ApplicationDTO>())

    val participantsList = MutableStateFlow(emptyList<ApplicationDTO>())

    @SuppressLint("SuspiciousIndentation")
    fun getLogin(post: Post, dataStoreManager: DataStoreManager){

        viewModelScope.launch {
        val response = repository.getLogin(post)
            dataStoreManager.saveEmail(
                emailData = post.email
            )
        myResponse1.value = response
        dataStoreManager.saveNames(
            namesData = PostNames(response.body()?.firstName.toString(),response.body()?.secondName.toString(), response.body()?.token.toString())
        )
        }

    }

    fun getRegister(postReg: PostReg, dataStoreManager: DataStoreManager){
        viewModelScope.launch {
            val response = repository.getRegister(postReg)

            dataStoreManager.saveNames(
                namesData = PostNames(response.body()?.firstName.toString(),response.body()?.secondName.toString(), response.body()?.token.toString())
            )
        }
    }

    fun getToken(token: TokenPost){
        viewModelScope.launch {
            val response = repository.getToken(token)
            tokenResponse.value = response.body()

        }
    }

    fun createEvent(postEvents: PostEvents){
        viewModelScope.launch {
            val response = repository.createEvent(postEvents)

        }
    }

   fun getEvents()  = runBlocking{
                val response = repository.getEvents()
                //myResponse3 = MutableLiveData(response.body()!!.titles)
                //response.body()?.let { EventTitles(it.titles) }
                eventsList.value = response.body()!!.titles
                response.body()?.let { EventTitles(it.titles) }
    }

    fun getEvent(title:String)  = runBlocking{
        val response = repository.getEvent(title)
        event.value = response.body()
        response.body()?.let { AboutEvent(it.id,it.sportType,it.dates,it.location,it.organizer,it.phoneNumber,it.owner) }
        //myResponse.value = response
    }

    fun createApplication(postApplication: PostApplication){
        viewModelScope.launch {
            val response = repository.createApplication(postApplication)

        }
    }

    fun getApplications(title:String)  = runBlocking{
        val response = repository.getApplications(title)
        //myResponse4 = MutableLiveData(response.body()!!.applications)
        applicationsList.value = response.body()!!.applications
        response.body()?.let { ApplicationsList(it.applications) }
    }

    fun approveApplication(id: String){
        viewModelScope.launch {
            val response = repository.approveApplication(id)

        }
    }

    fun dismissApplication(id: String){
        viewModelScope.launch {
            val response = repository.dismissApplication(id)

        }
    }

    fun getParticipants(title:String)  = runBlocking{
        val response = repository.getParticipants(title)
        //myResponse4 = MutableLiveData(response.body()!!.applications)
        participantsList.value = response.body()!!.applications
        response.body()?.let { ApplicationsList(it.applications) }
    }

}
