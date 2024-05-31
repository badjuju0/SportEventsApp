package com.example.sporteventsapp.api


import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val interceptor = HttpLoggingInterceptor()

object RetrofitInstance {

val client = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            //.baseUrl("http://10.0.2.2:8080").client(client)
            .baseUrl("https://badjuju0-sporteventsapp-backend-a0b1.twc1.net").client(client)
            //.baseUrl("http://192.168.1.110:8080").client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
//            .create(SimpleAPI::class.java)
    }

    val api  = retrofit.create(SimpleAPI::class.java)

}
