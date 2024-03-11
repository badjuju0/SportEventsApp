package com.example.sporteventsapp.api

import com.example.sporteventsapp.data.Post
import com.example.sporteventsapp.data.PostReg
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SimpleAPI {
    @POST("/login")
    suspend fun getLogin(
        @Body post: Post
    ): Response<Post>

    @POST("/register")
    suspend fun getRegister(
        @Body postReg: PostReg
    ): Response<Post>
}
