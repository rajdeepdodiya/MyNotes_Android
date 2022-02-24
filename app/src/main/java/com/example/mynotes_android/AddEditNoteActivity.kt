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
        val note = intent.getSerializableExtra("note") as Note

//        if( action == "add"){
//
//        }

        if(action == "edit"){
            binding.etNoteTitle.setText(note.noteTitle)
            binding.etNoteDescription.setText(note.description)
            binding.btnSave.text = "Update"
        }

        binding.btnSave.setOnClickListener{

            val title = binding.etNoteTitle.text.trim().toString()
            val description = binding.etNoteDescription.text.trim().toString()
            var toastMessage = ""

            val timeStamp = validateInputAndGenerateTimestamp(title, description)

            if(timeStamp != null){
                if(action == "add"){
                    viewModel.insertNote(Note(title, description, timeStamp))
                    toastMessage = "Note added"
                }

                if(action == "edit"){
                    viewModel.updateNote(Note(title, description, timeStamp))
                    toastMessage = "Note updated"
                }
            }

            else{
                toastMessage = "Please enter values for all fields"
            }
            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()

        }

    }
    private fun validateInputAndGenerateTimestamp(title: String, description: String): String? {
        return if(title.isNotEmpty() && description.isNotEmpty()){
            val sdf = SimpleDateFormat("MMM dd, yyyy - HH: mm")
            sdf.format(Date())

        } else{
            null
        }

    }

}