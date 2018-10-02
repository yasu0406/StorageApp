package com.example.katayama.storageapp

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Menu
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.katayama.storageapp.model.ImageUploadInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import java.io.IOException

class MainActivity : AppCompatActivity() {

    // Folder path for Firebase Storage.
    private val Storage_Path: String = "All_Image_Uploads/"

    // Root Database Name for Firebase Database.
    private val Database_Path: String = "All_Image_Uploads_Database"

    private var ChooseButton: Button? = null
    private var UploadButton: Button? = null
    private var ShowButton: Button? = null
    private var ImageName: EditText? = null
    private var ImageContent: EditText? = null
    private var SelectImage: ImageView? = null
    private var FilePathUri: Uri? = null
    private var storageReference: StorageReference? = null
    private var databaseReference: DatabaseReference? = null
    private val Image_Request_Code: Int = 7
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Assign FirebaseStorage instance to storageReference.
        storageReference = FirebaseStorage.getInstance().reference.child(Storage_Path)

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path)

        //Assign ID'S to button.
        ChooseButton = findViewById(R.id.ButtonChooseImage) as Button
        UploadButton = findViewById(R.id.ButtonUploadImage) as Button
        ShowButton = findViewById(R.id.ButtonShowImage) as Button

        // Assign ID's to EditText.
        ImageName = findViewById(R.id.ImageNameEditText) as EditText
        ImageContent =  findViewById(R.id.ImageContentEditText) as EditText
        SelectImage = findViewById(R.id.ShowImageView) as ImageView

        ChooseButton!!.setOnClickListener { view ->
            // Creating intent
            var intent = Intent()

            // Setting intent type as image to select image from phone storage.
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code)
        }

        UploadButton!!.setOnClickListener { view ->
            // Calling method to upload selected image on Firebase storage.
            UploadImageFileToFirebaseStorage()
        }

        ShowButton!!.setOnClickListener { view ->
            // Creating intent
            var intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.data != null) {
            FilePathUri = data.data

            try {
                // Getting selected image into Bitmap.
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, FilePathUri)

                // Setting up bitmap selected image into ImageView.
                SelectImage!!.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    fun GetFileExtension(uri: Uri):String {
        val contentResolver = contentResolver

        val mimeTypeMap: MimeTypeMap = MimeTypeMap.getSingleton()

        // Returning the file Extension.

        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri))
    }

    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    fun UploadImageFileToFirebaseStorage() {
        // Checking whether FilePathUri Is empty or not.
        if(FilePathUri != null) {
            val fileName = ImageName!!.text.toString()
            val content = ImageContent!!.text.toString()


            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading")
            progressDialog.show()

            // Creating second StorageReference.
            val storageReference2nd = storageReference!!.child(fileName)
            // Create the file metadata
            var metadata = StorageMetadata.Builder()
                    .setContentType("image/jpeg")
                    .build()

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri!!, metadata).addOnSuccessListener{ taskSnapshot ->
                progressDialog.dismiss()

                // Showing toast message after done uploading.
                Toast.makeText(applicationContext, "Image Uploaded Successfully ", Toast.LENGTH_SHORT).show()


                storageReference2nd!!.downloadUrl.addOnCompleteListener { taskSnapshot ->
                    var url = taskSnapshot.result.toString()
                    writeNewImageInfoToDB(fileName, url, content)
                }

            }.addOnFailureListener{ exception ->

                // Showing exception erro message.
                Toast.makeText(this@MainActivity, exception.message, Toast.LENGTH_SHORT).show()

            }
        } else {
            Toast.makeText(this@MainActivity, "Please Select Image or Add Image Name", Toast.LENGTH_SHORT).show()
        }

    }

    private fun writeNewImageInfoToDB(name: String, url: String, content: String) {
        val info = ImageUploadInfo(name, url, content)
        val key = databaseReference!!.push().key as String
        databaseReference!!.child(key).setValue(info)
    }
}