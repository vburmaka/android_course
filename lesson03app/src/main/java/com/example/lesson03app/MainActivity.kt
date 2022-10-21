package com.example.lesson03app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Switch
import android.widget.TextView

const val QUESTION_KEY = "QUESTION_KEY"
const val ANSWER_KEY = "ANSWER_KEY"
const val ANSWER_REQUEST_CODE_KEY = 111

class MainActivity : AppCompatActivity(), OnClickListener {

    private val TAG = "MainActivity"
    private var nextActivityButton: Button? = null
    private var button2: Button? = null
    private var switch: Switch? = null
    private var isButton2Enabled = false
    private var anwerTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.w(TAG, "onCreate: ", )

        nextActivityButton = findViewById(R.id.nextActivityButton)
        button2 = findViewById(R.id.button2)
        switch = findViewById(R.id.switch1)
        anwerTextView = findViewById(R.id.answerTextView)
        anwerTextView?.text = "Waiting for result"

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
        val intent = Intent(
            this,
            Activity2::class.java
        )
        intent.putExtra(QUESTION_KEY, "Are you happy?")
        anwerTextView?.text = "Waiting for result"

        startActivityForResult(intent, ANSWER_REQUEST_CODE_KEY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ANSWER_REQUEST_CODE_KEY){
            if (resultCode == RESULT_OK){
                anwerTextView?.text = "He (She) agree " + data?.getBooleanExtra(ANSWER_KEY, false)
            }
        }
    }
}