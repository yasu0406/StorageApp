package com.example.katayama.storageapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katayama.storageapp.R
import com.example.katayama.storageapp.model.ImageUploadInfo
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.widget.TextView




class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var mListArray: List<ImageUploadInfo>? = null
    private var context: Context? = null

    private fun Adapter(context: Context, templist: List<ImageUploadInfo>) {
        this.mListArray = templist

        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var view = ViewHolder(layoutInflater.inflate(R.layout.listview_row, parent , false))
        return view
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val imageUploadInfo: ImageUploadInfo = mListArray!!.get(position)
        holder.textViewName.setText(imageUploadInfo.imageName)
        Glide.with(context).load(imageUploadInfo.imageURL).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return mListArray!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewName: TextView
        var imageView: ImageView

        init {

            textViewName = itemView.findViewById(R.id.title) as TextView
            imageView = itemView.findViewById<View>(R.id.imageListView) as ImageView
        }
    }
}