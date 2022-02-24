package com.example.mynotes_android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes_android.databinding.RecyclerviewRowLayoutBinding
import com.example.mynotes_android.models.Note

class NotesAdapter(val context: Context,
                   val noteClickInterface: NoteClickInterface,
                   val noteDeleteClickInterface: NoteDeleteClickInterface): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NotesViewHolder(binding: RecyclerviewRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        val tvNoteTitle = binding.tvNoteTitle
        val tvTimeStamp = binding.tvTimeStamp
        val ibDelete = binding.ibDelete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = RecyclerviewRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]

        holder.tvNoteTitle.text = currentNote.noteTitle
        holder.tvTimeStamp.text = currentNote.timeStamp

        holder.itemView.setOnClickListener{
            noteClickInterface.onNoteClick(currentNote)
        }

        holder.ibDelete.setOnClickListener{
            noteDeleteClickInterface.onDeleteIconClick(currentNote)
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateNoteList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickInterface{
    fun onNoteClick(note: Note)
}
interface NoteDeleteClickInterface{
    fun onDeleteIconClick(note: Note)
}