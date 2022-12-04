package com.example.room.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.room.Room
import com.example.room.core.EventRepository
import com.example.room.data.db.DbEvent
import com.example.room.data.db.EventsDatabase
import com.example.room.models.EventModel
import java.util.*

private const val DATABASE_NAME = "events-db"
class EventRepositoryImpl(private val context: Context) : EventRepository {

    companion object {
        fun getInstance(context: Context): EventRepository = EventRepositoryImpl(context)
    }

    private val db: EventsDatabase = Room.databaseBuilder(
        context,
        EventsDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val eventDao = db.eventDao()


    override fun getAllEvents(): LiveData<List<EventModel>> = Transformations.map(eventDao.getEvents()){
        it.map { it.toEvent() }
    }

    override fun getEventById(id: UUID): LiveData<EventModel?> = Transformations.map(eventDao.getEvent(id)){
        it?.toEvent()
    }

    override suspend fun updateEvent(event: EventModel){
        eventDao.insertEvent(event.toDBEvent())
    }

    private fun DbEvent.toEvent() = EventModel(
        id = this.id,
        title = this.title,
        date = this.date
    )

    private fun EventModel.toDBEvent() =  DbEvent (
        id = this.id,
        title = this.title,
        date = this.date
    )
}


