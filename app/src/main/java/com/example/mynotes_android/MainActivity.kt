package com.example.mynotes_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes_android.adapter.NoteClickInterface
import com.example.mynotes_android.adapter.NoteDeleteClickInterface
import com.example.mynotes_android.adapter.NotesAdapter
import com.example.mynotes_android.databinding.ActivityMainBinding
import com.example.mynotes_android.models.Note
import com.example.mynotes_android.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteDeleteClickInterface {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAllNotes.layoutManager = LinearLayoutManager(this)

        val adapter = NotesAdapter(this, this, this)
        binding.rvAllNotes.adapter = adapter

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list.let {
                adapter.updateNoteList(it)
            }
        })

        binding.fabAddNote.setOnClickListener{
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            intent.putExtra("action", "add")
            startActivity(intent)
            this.finish()
        }

    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("note", note)
        intent.putExtra("action", "edit")
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
     viewModel.deleteNote(note.id)
        Toast.makeText(this, "${note.noteTitle} deleted", Toast.LENGTH_SHORT).show()

    }
}