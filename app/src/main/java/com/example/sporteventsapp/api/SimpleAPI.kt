package com.example.sporteventsapp.api

import com.example.sporteventsapp.data.AboutEvent
import com.example.sporteventsapp.data.Answer
import com.example.sporteventsapp.data.EventTitles
import com.example.sporteventsapp.data.Post
import com.example.sporteventsapp.data.PostEvents
import com.example.sporteventsapp.data.PostNames
import com.example.sporteventsapp.data.PostReg
import retrofit2.Call
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
    ): Response<PostNames>

    @POST("/createEvent")
    suspend fun createEvent(
        @Body postEvents: PostEvents
    ): Response<Answer>

    @GET("/getEvents")
suspend fun getEvents():Response<EventTitles>


    @GET("/getEvents/{title}")
    suspend fun getEvent(
        @Path("title") title: String
    ):Response<AboutEvent>
}
