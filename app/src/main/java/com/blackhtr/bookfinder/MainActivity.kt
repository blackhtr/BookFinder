package com.blackhtr.bookfinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blackhtr.bookfinder.data.DataRepository
import com.blackhtr.bookfinder.data.DataViewModel
import com.blackhtr.bookfinder.data.TotalData
import com.blackhtr.bookfinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var dataViewModel: DataViewModel
    private var mBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding!!.root)
        addObserver()
    }

    private fun initLayout(){
        /*mBinding?.rvBookList?.run {
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply { this.orientation = LinearLayoutManager.VERTICAL }
            //this.adapter = MainAdatper(this@MainActivity)
        }*/
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
        dataViewModel = DataViewModel(application, DataRepository())
        //dataViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        dataViewModel.TotalData.observe(this){
        }
    }

    private fun setTotalData(totalData:TotalData?){
        /*mBinding?.rvBookList?.adapter?.run {
            if(this is MainAdapter) this.setData(totalData)
        }*/
    }
}