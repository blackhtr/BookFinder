package com.blackhtr.bookfinder.data

import com.blackhtr.bookfinder.network.RetrofitManager

class DataRepository {
    suspend fun searchVolumes(keyWord:String) = RetrofitManager.service.searchBook(keyWord)
}