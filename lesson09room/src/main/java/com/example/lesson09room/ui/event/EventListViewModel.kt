package com.example.room.ui.event

import androidx.lifecycle.ViewModel
import com.example.room.core.EventRepository

class EventListViewModel(private val repository: EventRepository) : ViewModel() {
    val eventsLiveData = repository.getAllEvents()
}