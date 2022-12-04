package com.example.room.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "events")
data class DbEvent(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var title: String,
    var date: Date
)
