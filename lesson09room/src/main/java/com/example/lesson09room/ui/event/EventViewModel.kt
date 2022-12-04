package com.example.room.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.core.EventRepository
import com.example.room.models.EventModel
import kotlinx.coroutines.launch
import java.util.UUID

class EventViewModel(private val repository: EventRepository) : ViewModel() {


    fun getEvenLiveData(id: UUID) = repository.getEventById(id)

    fun updateEvent(eventModel: EventModel) {
        viewModelScope.launch {
            repository.updateEvent(eventModel)
        }
    }
}