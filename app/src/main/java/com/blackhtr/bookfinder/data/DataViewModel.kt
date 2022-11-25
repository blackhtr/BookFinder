package com.blackhtr.bookfinder.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataViewModel(app:Application, repository: DataRepository):AndroidViewModel(app) {
    private val mRepository = repository
    private val _TotalData = MutableLiveData<TotalData?>()
    val TotalData : LiveData<TotalData?> get() = _TotalData

    fun searchKeyword(keyWord:String, startIndex:Int){
        viewModelScope.launch {
            mRepository.searchVolumes(keyWord, startIndex).let { response ->
                _TotalData.value = if(response.isSuccessful) response.body() else null
            }
        }
    }

}