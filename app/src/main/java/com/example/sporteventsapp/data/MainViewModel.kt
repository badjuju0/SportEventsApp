package com.example.sporteventsapp.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sporteventsapp.api.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    var myResponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getLogin(post: Post){
        viewModelScope.launch {
            val response = repository.getLogin(post)
            myResponse.value = response
        }
    }
}