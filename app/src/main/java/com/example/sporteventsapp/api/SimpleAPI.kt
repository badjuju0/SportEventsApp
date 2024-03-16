package com.example.sporteventsapp.api

import com.example.sporteventsapp.data.Post
import com.example.sporteventsapp.data.PostNames
import com.example.sporteventsapp.data.PostReg
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SimpleAPI {
    @POST("/login")
    suspend fun getLogin(
        @Body post: Post
    ): Response<PostNames>

    @POST("/register")
    suspend fun getRegister(
        @Body postReg: PostReg
    ): Response<Post>


    @GET("/getNames/{email}")
    suspend fun getNames(
        @Path("email") email: String
    ):Response<PostNames>
}
