package com.blackhtr.bookfinder.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitManager {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create<RetrofitInterface>()
}