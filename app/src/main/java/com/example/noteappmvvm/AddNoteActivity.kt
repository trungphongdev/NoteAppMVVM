package com.example.noteappmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteappmvvm.database.Note
import com.example.noteappmvvm.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {
    private lateinit var noteTitleEdt: EditText
    private lateinit var noteDescriptionEdt: EditText
    private lateinit var addUpdateBtn: Button
    private lateinit var viewModel: NoteViewModel
    var noteId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        noteTitleEdt = findViewById(R.id.title_edt_note)
        noteDescriptionEdt = findViewById(R.id.description_note_edt)
        addUpdateBtn = findViewById(R.id.add_update_btn)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        val noteType = intent.getStringExtra("noteType")
        val note = intent.getParcelableExtra<Note>("note")
        if (noteType.equals("Edit") && note != null) {
            val noteTitle = note.noteTitle
            val noteDescription = note.noteDescription
            noteId = note?.id
            noteTitleEdt.setText(noteTitle)
            noteDescriptionEdt.setText(noteDescription)
            addUpdateBtn.setText("Update Note")

        } else {
            addUpdateBtn.setText("Add Note")
        }
        addUpdateBtn.setOnClickListener {
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteDescriptionEdt.text.toString()
            if (noteType.equals("Edit")) {
                val timeFormat = SimpleDateFormat("dd/MM/YYYY - hh:mm:ss")
                val currentTime = timeFormat.format(Date())
                val _note = Note(noteTitle,noteDescription,currentTime)
                _note.id = noteId
                viewModel.updateNote(_note)
                Toast.makeText(this,"Update Note Successfully",Toast.LENGTH_SHORT).show()
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val timeFormat = SimpleDateFormat("dd/MM/YYYY - hh:mm:ss")
                    val currentTime = timeFormat.format(Date())
                    val _note = Note(noteTitle, noteDescription, currentTime)
                    viewModel.insertNote(_note)
                    Toast.makeText(this,"Add Note Successfully",Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(this,MainActivity::class.java))
            this.finish()

        }

    }
}