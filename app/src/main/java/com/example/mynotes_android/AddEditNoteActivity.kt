package com.example.mynotes_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes_android.databinding.ActivityAddEditNoteBinding
import com.example.mynotes_android.models.Note
import com.example.mynotes_android.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditNoteBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val action = intent.getStringExtra("action")
        val note = intent.getSerializableExtra("note") as? Note
        val noteId = note?.id.let { note?.id } ?: -1

//        if( action == "add"){
//
//        }

        if(action == "edit"){
            binding.etNoteTitle.setText(note?.noteTitle)
            binding.etNoteDescription.setText(note?.description)
            binding.btnSave.text = "Update"
        }

        binding.btnSave.setOnClickListener{

            val title = binding.etNoteTitle.text.trim().toString()
            val description = binding.etNoteDescription.text.trim().toString()

            val timeStamp = validateInputAndGenerateTimestamp(title, description)

            if(timeStamp != null){

                val note = Note(title, description, timeStamp)

                if(action == "add"){
                    viewModel.insertNote(note)
                    Toast.makeText(this, "Note added", Toast.LENGTH_LONG).show()
                }

                if(action == "edit"){
                    note.id = noteId
                    viewModel.updateNote(note)
                    Toast.makeText(this, "Note updated", Toast.LENGTH_LONG).show()
                }
                this.finish()
            }

            else{
                Toast.makeText(this, "Please enter value for all fields", Toast.LENGTH_LONG).show()
            }
        }

    }
    private fun validateInputAndGenerateTimestamp(title: String, description: String): String? {
        return if(title.isNotEmpty() && description.isNotEmpty()){
            val sdf = SimpleDateFormat("MMM dd, yyyy - hh: mm a")
            sdf.format(Date())

        } else null
    }

}