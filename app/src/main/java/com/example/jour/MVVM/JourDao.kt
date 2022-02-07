package com.example.jour.MVVM

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface JourDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query(value = "Select * from jourTable  ")
    fun getAllEntries(): LiveData<List<Note>>
}