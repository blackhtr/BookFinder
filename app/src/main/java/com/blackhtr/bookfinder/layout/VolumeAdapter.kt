package com.blackhtr.bookfinder.layout

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blackhtr.bookfinder.R
import com.blackhtr.bookfinder.data.Item
import com.blackhtr.bookfinder.data.TotalData
import com.bumptech.glide.Glide

class VolumeAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private val mContext = context
    private var mDatas:MutableList<ItemInfo> = mutableListOf()

    companion object{
        const val VIEW_TYPE_RESULTS = 0
        const val VIEW_TYPE_ITEM = 1

        const val KEY_THUMBNAIL:String = "thumbnail"
        const val KEY_SMALL_THUMBNAIL:String = "smallThumbnail"
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data : TotalData?){
        mDatas.clear()
        mDatas.add(ItemInfo(VIEW_TYPE_RESULTS, data?.totalItems?:0))
        data?.items?.run {
            for(item in this){
                mDatas.add(ItemInfo(VIEW_TYPE_ITEM, item))
            }
        }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data : TotalData?){
        mDatas.removeAt(0)
        mDatas.add(0, ItemInfo(VIEW_TYPE_RESULTS, data?.totalItems?:0))
        data?.items?.run {
            for(item in this){
                mDatas.add(ItemInfo(VIEW_TYPE_ITEM, item))
            }
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = mDatas[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return when(viewType){
           VIEW_TYPE_RESULTS -> VolumeTotalHolder(parent)
           else -> VolumeHolder(parent).apply { setClickListener(this) }
       }
    }
    private fun setClickListener(holder: VolumeHolder){
        holder.itemView.setOnClickListener{
            val realPosition = holder.adapterPosition
            if(realPosition != RecyclerView.NO_POSITION && realPosition < mDatas.size){
                mContext.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    val volumeInfo = (mDatas[realPosition].value as Item).volumeInfo
                    data = Uri.parse(volumeInfo.infoLink)
                })
            }
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if( 0 <= position && position < mDatas.size){
            when(holder){
                is VolumeTotalHolder -> {
                    val totalCnt = mDatas[position].value
                    if(totalCnt is Int) holder.tvResultCnt.text = mContext.getString(R.string.results, totalCnt.toString())
                }
                is VolumeHolder -> {
                    val item = mDatas[position].value
                    if(item is Item){
                        var thumbnail:String = ""
                        item.volumeInfo.imageLinks?.run {
                            if(this.containsKey(KEY_THUMBNAIL)) thumbnail = this[KEY_THUMBNAIL]?:""
                            if(thumbnail.isBlank() && this.containsKey(KEY_SMALL_THUMBNAIL)) thumbnail = this[KEY_SMALL_THUMBNAIL]?:""
                        }
                        Glide.with(mContext).load(thumbnail).into(holder.ivVolumeThumbnail)
                        holder.tvVolumeTitle.text = item.volumeInfo.title
                        item.volumeInfo.authors.run {
                             holder.tvVolumeSubTitle.text = if(this.isNullOrEmpty()) "" else this[0]
                        }
                    }

                }
            }
        }
    }

    override fun getItemCount(): Int = mDatas.size
}

data class ItemInfo(val viewType:Int, val value: Any)

class VolumeTotalHolder(parent: ViewGroup) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_total_volume_holder, parent, false)){
    val tvResultCnt: TextView = itemView.findViewById(R.id.tvResultCnt)
}

class VolumeHolder(parent: ViewGroup) : ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_volume_holder, parent, false)){
    val ivVolumeThumbnail:ImageView = itemView.findViewById(R.id.ivVolumeThumbnail)
    val tvVolumeTitle:TextView = itemView.findViewById(R.id.tvVolumeTitle)
    val tvVolumeSubTitle:TextView = itemView.findViewById(R.id.tvVolumeSubTitle)
}