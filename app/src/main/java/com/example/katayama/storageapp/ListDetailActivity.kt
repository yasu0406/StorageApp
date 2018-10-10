package com.example.katayama.storageapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class ListDetailActivity : AppCompatActivity() {

    private var imageDetail: ImageView? = null
    private var title: TextView? = null
    private var content: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        val extras = intent.extras

        var imageURL = extras.get("imageURL")
        var imageName = extras.get("imageName")
        var imageContent = extras.get("imageContent")


        imageDetail = findViewById(R.id.imageDetail)
        title = findViewById(R.id.title)
        content = findViewById(R.id.content)

        Glide.with(imageDetail)
                .load(imageURL)
                .into(imageDetail)
        title!!.setText(imageName as String)
        content!!.setText(imageContent as String)


    }
}
