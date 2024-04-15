package com.example.sporteventsapp.data

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
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {


    var myResponse: MutableLiveData<Response<AboutEvent>> = MutableLiveData()

    val myResponse1: MutableLiveData<Response<PostNames>> = MutableLiveData()

    var myResponse2: List<EventTitle> by mutableStateOf(listOf())

    var myResponse3: MutableLiveData<List<EventTitle>> = MutableLiveData()

    fun getLogin(post: Post, dataStoreManager: DataStoreManager){

        viewModelScope.launch {
        val response = repository.getLogin(post)
        dataStoreManager.saveNames(
            namesData = PostNames(response.body()?.firstName.toString(),response.body()?.secondName.toString())
        )
        }

    }

    fun getRegister(postReg: PostReg, dataStoreManager: DataStoreManager){
        viewModelScope.launch {
            val response = repository.getRegister(postReg)

            dataStoreManager.saveNames(
                namesData = PostNames(response.body()?.firstName.toString(),response.body()?.secondName.toString())
            )
        }
    }

    fun createEvent(postEvents: PostEvents){
        viewModelScope.launch {
            val response = repository.createEvent(postEvents)

        }
    }

   fun getEvents()  = runBlocking{
                val response = repository.getEvents()
                myResponse3 = MutableLiveData(response.body()!!.titles)
                response.body()?.let { EventTitles(it.titles) }

    }

    fun getEvent(title:String)  = runBlocking{
        val response = repository.getEvent(title)
        myResponse.value = response
    }

}
