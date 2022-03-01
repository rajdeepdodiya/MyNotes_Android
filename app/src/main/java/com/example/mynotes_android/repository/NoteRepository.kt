package com.example.mynotes_android.repository

import androidx.lifecycle.LiveData
import com.example.mynotes_android.models.Note

class NoteRepository(private val noteDAO : NoteDAO) {

    val allNotes: LiveData<List<Note>> = noteDAO.getAllNotes()

    suspend fun insertNote(note: Note){
        noteDAO.insertNote(note)
    }

    suspend fun deleteNote(note:Note){
        noteDAO.deleteNote(note)
    }

    suspend fun updateNote(newNote: Note){
        noteDAO.updateNote(newNote)
    }
}