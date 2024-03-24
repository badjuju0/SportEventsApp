package com.example.sporteventsapp.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sporteventsapp.api.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {


    var myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    val myResponse1: MutableLiveData<Response<PostNames>> = MutableLiveData()

    var myResponse2: MutableLiveData<EventTitles> = MutableLiveData()



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


   fun getEvents(){
         viewModelScope.launch {
            val response = repository.getEvents()
             response.body()?.let { EventTitles(title = it.title) }


        }

    }
}
