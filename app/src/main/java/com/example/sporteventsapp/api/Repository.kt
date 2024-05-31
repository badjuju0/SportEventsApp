package com.example.sporteventsapp.api

import android.util.Log
import androidx.lifecycle.Observer
import com.example.sporteventsapp.data.AboutEvent
import com.example.sporteventsapp.data.Answer
import com.example.sporteventsapp.data.ApplicationsList
import com.example.sporteventsapp.data.DataStoreManager
import com.example.sporteventsapp.data.EventTitles
import com.example.sporteventsapp.data.Post
import com.example.sporteventsapp.data.PostApplication
import com.example.sporteventsapp.data.PostEvents
import com.example.sporteventsapp.data.PostNames
import com.example.sporteventsapp.data.PostReg
import com.example.sporteventsapp.data.TokenPost
import retrofit2.Response
import retrofit2.http.POST

class Repository() {
    suspend fun getLogin(post: Post): Response<PostNames> {
        return RetrofitInstance.api.getLogin(post)


    }

    suspend fun getRegister(postReg: PostReg): Response<PostNames> {
        return RetrofitInstance.api.getRegister(postReg)
    }

    suspend fun getToken(tokenPost: TokenPost): Response<Answer> {
        return RetrofitInstance.api.getToken(tokenPost)
    }

    suspend fun getEvents(): Response<EventTitles> {
        return RetrofitInstance.api.getEvents()

    }

    suspend fun createEvent(postEvents: PostEvents): Response<Answer> {
        return RetrofitInstance.api.createEvent(postEvents)
    }

    suspend fun getEvent(title: String): Response<AboutEvent> {
        return RetrofitInstance.api.getEvent(title)
    }

    suspend fun createApplication(postApplication: PostApplication): Response<Answer> {
        return RetrofitInstance.api.createApplication(postApplication)
    }

    suspend fun getApplications(title: String): Response<ApplicationsList> {
        return RetrofitInstance.api.getApplications(title)

    }

    suspend fun approveApplication(id: String): Response<Answer> {
        return RetrofitInstance.api.approveApplication(id)
    }

    suspend fun dismissApplication(id: String): Response<Answer> {
        return RetrofitInstance.api.dismissApplication(id)
    }

    suspend fun getParticipants(title: String): Response<ApplicationsList> {
        return RetrofitInstance.api.getParticipants(title)

    }

}

