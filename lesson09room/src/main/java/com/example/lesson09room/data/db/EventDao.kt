package com.example.room.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.UUID

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getEvents(): LiveData<List<DbEvent>>

    @Query("SELECT * FROM events WHERE id=(:id)")
    fun getEvent(id: UUID): LiveData<DbEvent?>

    @Insert(onConflict = OnConflictStrategy.IGNORE )
    suspend fun insertEvent(event: DbEvent)

    @Delete
    fun delete(event: DbEvent)
}