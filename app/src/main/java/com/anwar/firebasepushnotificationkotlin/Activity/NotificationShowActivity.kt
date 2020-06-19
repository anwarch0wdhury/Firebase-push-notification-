package com.anwar.firebasepushnotificationkotlin.Activity

import android.content.Context
import android.os.Bundle
import android.util.Base64
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.anwar.firebasepushnotificationkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_message_show.*


class NotificationShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_show)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //receive data from MyFirebaseNotificationService class
        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")
        val timeStampString = intent.getStringExtra("timestamp")
        val articleString = intent.getStringExtra("article_data")
        val imageUrl = intent.getStringExtra("image")

        //Set data on UI
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(featureGraphics)

        //Sharedpreference
        val sharedPreference =  getSharedPreferences("Push_notification", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()


        // Saving the data into sharedpreference by encoding it.
        val data: ByteArray = title.toByteArray()
        val base64title: String = Base64.encodeToString(data, Base64.DEFAULT)
        editor.putString("title",base64title)
        editor.commit()

       //show
        header.text = title
        messages.text = message
        timeStamp.text = timeStampString
        article.text = articleString
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = NavUtils.getParentActivityIntent(this)
        startActivity(intent)
    }
}