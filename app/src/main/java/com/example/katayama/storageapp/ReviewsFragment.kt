package com.example.katayama.storageapp

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView

class ReviewsFragment: Fragment() {
    var detailText: TextView? = null
    var relMain: RelativeLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater!!.inflate(R.layout.fragment_detail, container, false)

        relMain = view?.findViewById(R.id.rel_main) as RelativeLayout
        detailText = view?.findViewById(R.id.detailTextView) as TextView
        detailText!!.text = "test2"
        return view
    }
}