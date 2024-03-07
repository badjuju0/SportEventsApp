package com.example.sporteventsapp.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//            .create(SimpleAPI::class.java)
    }

    val api  = retrofit.create(SimpleAPI::class.java)

}