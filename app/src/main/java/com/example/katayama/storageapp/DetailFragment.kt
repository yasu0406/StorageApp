package com.example.katayama.storageapp

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.*

class DetailFragment : Fragment() {

    var detailText: TextView? = null
    var relMain: RelativeLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View? = inflater!!.inflate(R.layout.fragment_detail, container, false)

        relMain = view?.findViewById(R.id.rel_main) as RelativeLayout
        detailText = view?.findViewById(R.id.detailTextView) as TextView

        val extras = activity!!.intent.extras

        var imageContent = extras.getString("imageContent")
        detailText!!.text = imageContent

        return view
    }



}
