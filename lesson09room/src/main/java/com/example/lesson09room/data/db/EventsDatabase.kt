package com.example.room.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(EventTypeConverters::class)
@Database(entities = [DbEvent::class], version = 1)
abstract class EventsDatabase : RoomDatabase(){
    abstract fun eventDao(): EventDao
}