package com.blackhtr.bookfinder

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.blackhtr.bookfinder.data.DataRepository
import com.blackhtr.bookfinder.data.DataViewModel
import com.blackhtr.bookfinder.data.TotalData
import com.blackhtr.bookfinder.databinding.ActivityMainBinding
import com.blackhtr.bookfinder.layout.VolumeAdapter

class MainActivity : AppCompatActivity(){
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
        showLoading(false)
        mBinding?.rvBookList?.run {
            this.layoutManager = LinearLayoutManager(this@MainActivity).apply { this.orientation = LinearLayoutManager.VERTICAL }
            this.adapter = VolumeAdapter(this@MainActivity)
            this.addOnScrollListener(object: OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    mBinding?.rvBookList?.run {
                        mBinding?.etSearchBookName?.text?.toString()?.let {
                            if( this.adapter is VolumeAdapter && this.canScrollVertically(-1) && RecyclerView.SCROLL_STATE_IDLE == newState){
                                showLoading(true)
                                dataViewModel.searchKeyword(it, (this.adapter as VolumeAdapter).itemCount -1)
                            }
                        }
                    }
                }
            })
        }
        mBinding?.ivSearchBtn?.setOnClickListener { searchAction() }
        mBinding?.etSearchBookName?.run {
            this.imeOptions = EditorInfo.IME_ACTION_SEARCH
            setOnEditorActionListener { _, actionId, _ ->
                if(EditorInfo.IME_ACTION_SEARCH == actionId){
                    searchAction()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
            requestFocus()
        }
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
        dataViewModel.TotalData.observe(this){ setTotalData(it) }
    }

    private fun setTotalData(totalData:TotalData?){
        showLoading(false)
        mBinding?.rvBookList?.adapter?.run {
            if(this is VolumeAdapter){
                if(null != totalData && 1 < this.itemCount) this.addData(totalData)
                else this.setData(totalData)
            }
        }
    }


    private fun showLoading(isShow:Boolean){
        mBinding?.loadingIndicator?.visibility = if(isShow) View.VISIBLE else View.GONE
    }
    private fun searchAction(){
        setTotalData(null)
        showLoading(true)
        mBinding?.etSearchBookName?.text?.toString()?.run {
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(mBinding?.etSearchBookName?.windowToken, 0)
            dataViewModel.searchKeyword(this, 0)
        }
    }
}