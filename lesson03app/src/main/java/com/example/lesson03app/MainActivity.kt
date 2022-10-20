package com.example.lesson03app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Switch

class MainActivity : AppCompatActivity(), OnClickListener {

    val TAG = "MainActivity"
    var nextActivityButton: Button? = null
    var button2: Button? = null
    var switch: Switch? = null
    var isButton2Enabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.w(TAG, "onCreate: ", )
        nextActivityButton = findViewById(R.id.nextActivityButton)
        button2 = findViewById(R.id.button2)
        switch = findViewById(R.id.switch1)

        switch?.setOnClickListener(this)
        nextActivityButton?.setOnClickListener(this)
        button2?.setOnClickListener(this)

    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.nextActivityButton -> startActivity2()
            R.id.button2 -> nextActivityButton?.isEnabled = true
            R.id.switch1 -> {
                button2?.isEnabled = !isButton2Enabled
                isButton2Enabled = !isButton2Enabled
            }
        }
    }

    private fun startActivity2(){
        startActivity(
            Intent(
                this,
                Activity2::class.java
            )
        )
    }
}