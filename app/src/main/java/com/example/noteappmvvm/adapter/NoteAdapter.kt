package com.example.noteappmvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappmvvm.R
import com.example.noteappmvvm.database.Note

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){
    var OnDeleteIconOnClick: ((Note) -> Unit)? = null
    var OnItemOnClick: ((Note) -> Unit)? = null
    val list = ArrayList<Note>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleNoteTXV = itemView.findViewById<TextView>(R.id.tilte_txv_note_row)
        val timeStampNoteTXt: TextView = itemView.findViewById(R.id.timestamp_txv_note_row)
        val deleteNoteImg: ImageView = itemView.findViewById(R.id.delete_img_note_row)
        fun bind(note: Note) {
            titleNoteTXV.setText(note.noteTitle)
            timeStampNoteTXt.setText("Last Update : ${note.timeStamp}")
            deleteNoteImg.setOnClickListener {
                OnDeleteIconOnClick?.invoke(note)
            }
            itemView.setOnClickListener {
                OnItemOnClick?.invoke(note)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = list[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun updateList(newList: List<Note>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

}