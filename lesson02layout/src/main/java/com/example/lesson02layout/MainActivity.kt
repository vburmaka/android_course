package com.example.lesson02layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private var mainEditText: EditText? = null
    private var sevenButton: Button? = null
    private var eightButton: Button? = null
    private var nineButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainEditText = findViewById(R.id.mainEditText)

        sevenButton = findViewById(R.id.sevenButton)
        eightButton = findViewById(R.id.eightButton)
        nineButton = findViewById(R.id.nineButton)

        sevenButton?.setOnClickListener(this)
        eightButton?.setOnClickListener(this)
        nineButton?.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        mainEditText?.let {
            when (view.id) {
                R.id.sevenButton -> mainEditText?.setText(it.text.toString() + "7")
                R.id.eightButton -> mainEditText?.setText(it.text.toString() + "8")
                R.id.nineButton -> mainEditText?.setText(it.text.toString() + "9")
                else ->{}
            }
        }
    }
}