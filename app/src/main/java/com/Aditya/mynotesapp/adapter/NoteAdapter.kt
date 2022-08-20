package com.Aditya.mynotesapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.Aditya.mynotesapp.CustomOnItemClickListener
import com.Aditya.mynotesapp.NoteAddUpdateActivity
import com.Aditya.mynotesapp.R
import com.Aditya.mynotesapp.databinding.ItemNoteBinding
import com.Aditya.mynotesapp.entity.Note

class NoteAdapter(private val activity: Activity) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNotes = ArrayList<Note>()
        set(listNotes) {
            if (this.listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int = listNotes.size

    fun addItem(note: Note) {
        listNotes.add(note)
        notifyItemInserted(listNotes.size - 1)
    }

    fun updateItem(position: Int, note: Note) {
        listNotes[position] = note
        notifyItemChanged(position, note)
    }

    fun removeItem(position: Int) {
        listNotes.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listNotes.size)
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)
        fun bind(note: Note) {
            binding.apply {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener(
                    CustomOnItemClickListener(
                        adapterPosition,
                        object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(view: View, position: Int) {
                                val intent =
                                    Intent(activity, NoteAddUpdateActivity::class.java).apply {
                                        putExtra(NoteAddUpdateActivity.EXTRA_POSITION, position)
                                        putExtra(NoteAddUpdateActivity.EXTRA_NOTE, note)
                                    }
                                activity.startActivityForResult(
                                    intent,
                                    NoteAddUpdateActivity.REQUEST_UPDATE
                                )
                            }
                        })
                )
            }
        }
    }
}