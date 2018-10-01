package com.example.katayama.storageapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.katayama.storageapp.R
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.katayama.storageapp.model.ImageUploadInfo


class ListAdapter(context: Context) : BaseAdapter() {

    private var mlayoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private var mListArray: ArrayList<ImageUploadInfo>? = null

    override fun getCount(): Int {
        return mListArray!!.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        var convertView = mlayoutInflater!!.inflate(R.layout.listview_row, viewGroup, false)

        var title = convertView!!.findViewById<TextView>(R.id.title)
        title.setText(mListArray!!.get(position).imageName)
        var content = convertView!!.findViewById<TextView>(R.id.content)
        content.setText(mListArray!!.get(position).imageContent)
        var imageList = convertView!!.findViewById<ImageView>(R.id.imageListView)
        Glide.with(imageList)
                .load(mListArray!!.get(position).imageURL)
                .into(imageList)

        return convertView
    }

    fun setlistArray(listArray: ArrayList<ImageUploadInfo>?) {
        mListArray = listArray
    }

    override fun getItem(position: Int): Any {
        return mListArray!!.get(position)
    }

}