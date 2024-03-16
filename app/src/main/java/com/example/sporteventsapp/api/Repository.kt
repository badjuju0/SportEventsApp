package com.example.sporteventsapp.api

import android.util.Log
import androidx.lifecycle.Observer
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.Post
import com.example.sporteventsapp.data.PostNames
import com.example.sporteventsapp.data.PostReg
import retrofit2.Response
import retrofit2.http.POST

class Repository() {
    suspend fun getLogin(post: Post): Response<PostNames> {
        return RetrofitInstance.api.getLogin(post)


    }

    suspend fun getRegister(postReg: PostReg): Response<Post> {
        return RetrofitInstance.api.getRegister(postReg)
    }

    suspend fun getNames(email:String): Response<PostNames> {
        return RetrofitInstance.api.getNames(email)
    }
}

