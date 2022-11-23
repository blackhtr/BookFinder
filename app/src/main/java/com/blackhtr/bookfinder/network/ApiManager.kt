package com.blackhtr.bookfinder.network

import com.blackhtr.bookfinder.data.TotalData
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    interface ResponseCallBack{
        fun getResponse(data : TotalData?)
    }

    fun searchVolumes(keyWord:String, cb : ResponseCallBack){
        val service = retrofit.create<RetrofitInterface>()
        service.searchBook(keyWord).enqueue(object : Callback<TotalData> {
            override fun onResponse(call: Call<TotalData>, response: Response<TotalData>) { cb.getResponse(if(response.isSuccessful) response.body() else null) }
            override fun onFailure(call: Call<TotalData>, t: Throwable) { cb.getResponse(null) }
        })
    }


}