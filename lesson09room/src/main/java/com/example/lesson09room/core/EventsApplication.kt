package com.example.room.core

import android.app.Application
import com.example.room.data.EventRepositoryImpl

class EventsApplication : Application() {

    lateinit var repository: EventRepository

    override fun onCreate() {
        super.onCreate()
        repository = EventRepositoryImpl.getInstance(this)
    }
}