package com.example.katayama.storageapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.katayama.storageapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_list_detail.*


class ListDetailActivity : AppCompatActivity() {

    private var imageDetail: ImageView? = null
    private var title: TextView? = null

    internal lateinit var viewpageradapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)
        viewpageradapter = ViewPagerAdapter(supportFragmentManager)

        this.viewPager.adapter = viewpageradapter
        this.tab_layout.setupWithViewPager(this.viewPager)

        val extras = intent.extras

        var imageURL = extras.get("imageURL")
        var imageName = extras.get("imageName")

        imageDetail = findViewById(R.id.imageDetail)
        title = findViewById(R.id.title)

        Glide.with(imageDetail)
                .load(imageURL)
                .into(imageDetail)
        title!!.setText(imageName as String)


    }
}
