package com.example.room.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson09room.R
import com.example.room.ui.event.EventListFragment
import com.example.room.ui.event.EventFragment
import com.example.room.ui.event.OnEvenSelectedListener

class MainActivity : AppCompatActivity(), OnEvenSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null){
            val fragment = EventListFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }

    override fun onEventSelected(eventId: String, title: String) {
        val fragment = EventFragment.newInstance(eventId, title)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
    }
}