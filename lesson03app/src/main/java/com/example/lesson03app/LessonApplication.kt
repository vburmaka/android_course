package com.example.lesson03app

import android.app.Application
import android.util.Log

class LessonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.w("Demo", "onCreate: !!!!!!!!!!!!!!!!!!", )
    }
}