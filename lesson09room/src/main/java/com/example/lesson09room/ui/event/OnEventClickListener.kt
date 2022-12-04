package com.example.room.ui.event

import java.util.UUID

interface OnEventClickListener {
    fun onEventClicked(id: UUID, title: String)
}