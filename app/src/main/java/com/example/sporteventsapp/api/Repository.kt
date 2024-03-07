package com.example.sporteventsapp.api

import com.example.sporteventsapp.data.Post
import retrofit2.Response
import retrofit2.http.POST

class Repository {
    suspend fun getLogin(post: Post): Response<Post> {
        return RetrofitInstance.api.getLogin(post)
    }
}

