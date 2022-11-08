package com.example.lesson08lists

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment = supportFragmentManager.findFragmentByTag(ExampleListFragment.TAG)
            ?: ExampleListFragment.newInstance(1)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, listFragment, ExampleListFragment.TAG)
            .commit()
    }
}