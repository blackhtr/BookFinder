package com.blackhtr.bookfinder.data

import com.blackhtr.bookfinder.network.RetrofitManager

class DataRepository {
    fun searchVolumes(keyWord:String):TotalData? = RetrofitManager.service.searchBook(keyWord)
}