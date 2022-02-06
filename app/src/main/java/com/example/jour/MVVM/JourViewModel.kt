package com.example.jour.MVVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JourViewModel(application: Application): AndroidViewModel(application){
    val allEntries: LiveData<List<Note>>
    val repository: JourRepository

    init {
        val dao = JourDatabase.getDatabase(application).getEntitiesDao()
        repository= JourRepository(dao)
        allEntries=repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}