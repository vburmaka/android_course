package com.example.room.data.db

import androidx.room.TypeConverter
import java.util.*

class EventTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?) = date?.time

    @TypeConverter
    fun toDate(millis: Long?) = millis?.let {
        Date(it)
    }

    @TypeConverter
    fun toUuid(uuid: String) = UUID.fromString(uuid)

    @TypeConverter
    fun from(uuid: UUID?) = uuid?.toString()
}