package com.example.mynotes_android.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes_android.models.Note

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(newNote: Note)

    @Delete
    suspend fun  deleteNote(noteId: Int)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}