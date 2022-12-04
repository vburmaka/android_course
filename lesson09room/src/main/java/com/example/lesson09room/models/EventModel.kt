package com.example.room.models

import java.util.*

data class EventModel(val id:UUID = UUID.randomUUID(),
                    var title: String = "",
                    var date: Date = Date())
