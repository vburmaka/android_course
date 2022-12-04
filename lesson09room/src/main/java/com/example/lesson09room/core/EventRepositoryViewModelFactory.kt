package com.example.room.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room.ui.event.EventListViewModel
import com.example.room.ui.event.EventViewModel

class EventRepositoryViewModelFactory(private val repository: EventRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventViewModel(repository) as T
    }
}

class EventListRepositoryViewModelFactory(private val repository: EventRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EventListViewModel(repository) as T
    }
}