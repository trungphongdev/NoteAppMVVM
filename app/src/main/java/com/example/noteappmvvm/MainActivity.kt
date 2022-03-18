package com.example.noteappmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappmvvm.adapter.NoteAdapter
import com.example.noteappmvvm.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var noteRV: RecyclerView
    private lateinit var addFAB: FloatingActionButton
    private lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteRV = findViewById(R.id.recyclerviewNote)
        addFAB = findViewById(R.id.float_btn_add)
        val noteAdapter = NoteAdapter()
        noteRV.adapter = noteAdapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {
            it?.let {
                noteAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            val intent = Intent(this,AddNoteActivity::class.java)
            startActivity(intent)
            finish()
        }
        noteAdapter.OnDeleteIconOnClick = {
            viewModel.deleteNote(it)
            Toast.makeText(this,"${it.noteTitle} is deleted",Toast.LENGTH_LONG).show()

        }
        noteAdapter.OnItemOnClick = {
            val intent = Intent(this,AddNoteActivity::class.java)
            intent.putExtra("noteType","Edit")
            intent.putExtra("note",it)
            startActivity(intent)
            finish()
        }
    }
}