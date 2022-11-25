package com.blackhtr.bookfinder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackhtr.bookfinder.data.DataRepository
import com.blackhtr.bookfinder.data.DataViewModel
import com.blackhtr.bookfinder.data.TotalData
import com.blackhtr.bookfinder.databinding.ActivityMainBinding
import com.blackhtr.bookfinder.layout.VolumeAdapter

class MainActivity : AppCompatActivity() {
    lateinit var dataViewModel: DataViewModel
    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        initLayout()
        addObserver()
    }

    private fun initLayout(){
        Log.i("TRHEO", "initLayout")
        mBinding?.rvBookList?.run {
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply { this.orientation = LinearLayoutManager.VERTICAL }
            this.adapter = VolumeAdapter(this@MainActivity)
        }
        mBinding?.ivSearchBtn?.setOnClickListener {
            Log.i("TRHEO", "ivSearchBtn click")
            mBinding?.etSearchBookName?.text?.toString()?.run {
                dataViewModel.searchKeyword(this)
            }
        }
        mBinding?.etSearchBookName?.requestFocus()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun addObserver(){
        Log.i("TRHEO", "addObserver")
        dataViewModel = DataViewModel(application, DataRepository())
        //dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        dataViewModel.TotalData.observe(this){ setTotalData(it) }

    }

    private fun setTotalData(totalData:TotalData?){
        Log.i("TRHEO", "setTotalData")
        mBinding?.rvBookList?.adapter?.run {
            if(this is VolumeAdapter) this.setData(totalData)
        }
    }


}