package itesm.com.tecstore

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import itesm.com.tecstore.R.id.configurationButton_feed

class Feed : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)


        val configurationButton_feed = findViewById<ImageView>(R.id.configurationButton_feed) as ImageView
        // set on-click listener
        configurationButton_feed!!.setOnClickListener { startActivity(Intent(this@Feed, Menu::class.java)) }

    }
}
