package com.anwar.firebasepushnotificationkotlin.Activity

import android.content.Context
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anwar.firebasepushnotificationkotlin.Notification.Configuration.Companion.TOPIC_GLOBAL
import com.anwar.firebasepushnotificationkotlin.R
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //shredpreference to see previous notification title
        val sharedPreference =  getSharedPreferences("Push_notification", Context.MODE_PRIVATE)
        val mytitle=sharedPreference.getString("title","")


        // Receiving SP data and decoded into string
        val data: ByteArray = Base64.decode(mytitle, Base64.DEFAULT)
        val decodetitle = String(data)

        minetitle.text = "Last Notification Title: \n"+ "Encoded : "+ mytitle +"\nDecoded : " + decodetitle

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC_GLOBAL)
            .addOnCompleteListener { task ->

                if (task.isSuccessful)
                    Log.d(TAG, "Global topic subscription successful")
                else
                    Log.e(TAG, "Global topic subscription failed. Error: " + task.exception?.localizedMessage)
            }
    }
}
