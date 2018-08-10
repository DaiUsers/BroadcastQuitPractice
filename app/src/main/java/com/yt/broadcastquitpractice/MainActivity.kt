package com.yt.broadcastquitpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.force_offline)

        button.setOnClickListener({
            val intent = Intent("com.example.broadcastbestpractice.FORCE_OFFLINE")

            sendBroadcast(intent)
        })
    }
}
