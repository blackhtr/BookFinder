package com.blackhtr.bookfinder.network

import com.blackhtr.bookfinder.data.TotalData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("books/v1/volumes")
    suspend fun searchBook(@Query("q") q : String, @Query("maxResults") maxResults:Int, @Query("startIndex") startIndex:Int) : Response<TotalData>
}