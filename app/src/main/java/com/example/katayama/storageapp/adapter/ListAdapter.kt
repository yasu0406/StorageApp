package com.example.katayama.storageapp.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.katayama.storageapp.R
import android.widget.TextView
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

        var titleList = convertView!!.findViewById<TextView>(R.id.title)
        titleList.setText(mListArray!!.get(position).imageName)
        var imageList = convertView!!.findViewById<ImageView>(R.id.imageListView)
        imageList.setImageURI(mListArray!!.get(position).imageURL as Uri)

        return convertView
    }

    fun setlistArray(listArray: ArrayList<ImageUploadInfo>?) {
        mListArray = listArray
    }

    override fun getItem(p0: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}