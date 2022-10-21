package com.example.lesson03app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class Activity2 : AppCompatActivity() {

    val TAG = "Activity2"

    private var agreeCheckBox: CheckBox? = null
    private var questionTextView: TextView? = null
    private var sendResultButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        Log.w(TAG, "onCreate: ", )

        agreeCheckBox = findViewById(R.id.activityTwoCheckBox)
        questionTextView = findViewById(R.id.activityTwoTextView)
        sendResultButton = findViewById(R.id.sendAnswerButton)

        sendResultButton?.setOnClickListener{
            val intent = Intent()
            intent.putExtra(ANSWER_KEY, agreeCheckBox?.isChecked)
            setResult(RESULT_OK, intent)
            finish()
        }

        questionTextView?.text = intent.extras?.getString(QUESTION_KEY)
    }
}