package com.example.mynotes_android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mynotes_android.models.Note
import com.example.mynotes_android.repository.NoteDatabase
import com.example.mynotes_android.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init{
        val dao = NoteDatabase.getDatabase(application).getDAO()

        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(noteId: Int) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteNote(noteId)
    }

    fun updateNote(newNote: Note) = viewModelScope.launch(Dispatchers.IO){
        repository.updateNote(newNote)
    }

    fun insertNote(note: Note){ viewModelScope.launch(Dispatchers.IO){
        repository.insertNote(note)
    }}

}