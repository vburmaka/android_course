package com.example.lesson02layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titleTextView = findViewById<TextView>(R.id.mainActivityTitleTextView)
        val button = findViewById<Button>(R.id.mainActivityButton)

        button.setOnClickListener(object : OnClickListener{
            override fun onClick(p0: View?) {
                titleTextView.text = button.text
            }
        })
    }
}