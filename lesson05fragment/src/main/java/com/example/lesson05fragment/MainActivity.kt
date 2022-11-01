package com.example.lesson05fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val firstFragment = FirstFragment.newInstance("Custom param", 100)

    supportFragmentManager
        .beginTransaction()
        .add(R.id.main_container, firstFragment, "TAG")
        .commit()
    }
}