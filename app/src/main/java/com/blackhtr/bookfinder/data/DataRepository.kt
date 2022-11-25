package com.blackhtr.bookfinder.data

import com.blackhtr.bookfinder.network.RetrofitManager

class DataRepository {
    companion object{
        const val MAX_RESULTS_COUNT:Int = 30
    }
    suspend fun searchVolumes(keyWord:String, startIndex:Int) = RetrofitManager.service.searchBook(keyWord, MAX_RESULTS_COUNT, startIndex)
}