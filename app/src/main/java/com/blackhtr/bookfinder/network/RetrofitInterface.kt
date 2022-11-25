package com.blackhtr.bookfinder.network

import com.blackhtr.bookfinder.data.TotalData
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("books/v1/volumes")
    fun searchBook(@Query("q") q : String) : TotalData?
}