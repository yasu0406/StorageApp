package com.example.katayama.storageapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.katayama.storageapp.adapter.ListAdapter
import com.example.katayama.storageapp.model.ImageUploadInfo
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference


class ListActivity : AppCompatActivity() {

    // Root Database Name for Firebase Database.
    private val Database_Path: String = "All_Image_Uploads_Database"

    // Folder path for Firebase Storage.
    private val Storage_Path: String = "All_Image_Uploads/"

    //database reference
    private var databaseReference: DatabaseReference? = null

    // storage reference
    private var storageReference: StorageReference? = null

    //list to hold all the uploaded images
    private var imageUploadInfos: ArrayList<ImageUploadInfo>? = null

    // adapter
    private var listAdapter: ListAdapter? = null
    private var listMap: MutableMap<String, Any>? = null

    var mListView: ListView? = null

    var imageName:String? = null
    var imageURL: String? = null
    var imageContent: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path)
        mListView = findViewById(R.id.listView) as ListView
        imageUploadInfos = ArrayList<ImageUploadInfo>()
        listAdapter = ListAdapter(this)
        imageUploadInfos!!.clear()
        listAdapter!!.setlistArray(imageUploadInfos)
        mListView!!.setAdapter(listAdapter)
        listViewDatas()

    }

    private fun listViewDatas() {
        var mChildEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                listMap = dataSnapshot.getValue() as MutableMap<String, Any>
                imageName = listMap!!.get("imageName") as String
                imageURL = listMap!!.get("imageURL") as String
                imageContent = listMap!!.get("imageContent") as String


                var imageUploadInfo = ImageUploadInfo(imageName, imageURL, imageContent)
                imageUploadInfos!!.add(imageUploadInfo)
                listAdapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        databaseReference!!.addChildEventListener(mChildEventListener)
    }
}
