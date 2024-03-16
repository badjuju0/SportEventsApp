package com.example.sporteventsapp.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sporteventsapp.api.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {


    var myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    val myResponse1: MutableLiveData<Response<PostNames>> = MutableLiveData()


    fun getLogin(post: Post, dataStoreManager: DataStoreManager){
        viewModelScope.launch {
            val response = repository.getLogin(post)

            dataStoreManager.saveNames(
                namesData = PostNames(response.body()?.firstName.toString(),response.body()?.secondName.toString())
            )
        }
    }

    fun getRegister(postReg: PostReg){
        viewModelScope.launch {
            val response = repository.getRegister(postReg)
            myResponse.value = response
        }
    }

    fun getNames(email:String){
        viewModelScope.launch {
            val response = repository.getNames(email)
            myResponse1.value = response
        }
    }
}
