package com.example.katayama.storageapp

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

class ReviewsFragment: Fragment() {
    var detailText: TextView? = null
    var relMain: RelativeLayout? = null
    var floatingActionButton: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater!!.inflate(R.layout.fragment_reviews, container, false)

        relMain = view?.findViewById(R.id.rel_main) as RelativeLayout
        detailText = view?.findViewById(R.id.detailTextView) as TextView
        floatingActionButton = view!!.findViewById(R.id.floatingActionButton) as FloatingActionButton
        detailText!!.text = "test2"

        floatingActionButton!!.setOnClickListener {view ->
            var intent = Intent(view.context, ReviewWriteActivity::class.java)
            intent.putExtra("imageContent", "test")
            startActivity(intent)
        }
        return view
    }
}