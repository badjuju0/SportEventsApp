package com.example.sporteventsapp.api


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val interceptor = HttpLoggingInterceptor()

object RetrofitInstance {

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080").client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//            .create(SimpleAPI::class.java)
    }

    val api  = retrofit.create(SimpleAPI::class.java)


}