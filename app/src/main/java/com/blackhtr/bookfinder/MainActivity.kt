package com.blackhtr.bookfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.blackhtr.bookfinder.data.TotalData
import com.blackhtr.bookfinder.network.ApiManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiManager.searchVolumes("flower", object :ApiManager.ResponseCallBack{
            override fun getResponse(data: TotalData?) {
                Log.i("TRHEO", data.toString())
            }
        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}