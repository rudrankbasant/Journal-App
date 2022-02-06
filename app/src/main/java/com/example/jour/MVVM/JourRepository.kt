package com.example.jour.MVVM

import androidx.lifecycle.LiveData

class JourRepository(private val jourDao: JourDao) {

    val allNotes: LiveData<List<Note>> = jourDao.getAllEntries()


    suspend fun insert(note: Note){
        jourDao.insert(note)
    }

    suspend fun delete(note: Note){
        jourDao.delete(note)
    }

    suspend fun update(note: Note){
        jourDao.update(note)
    }
}