package com.example.noteappmvvm.database

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDAO: NotesDAO) {
    val allNotes: LiveData<List<Note>> = notesDAO.getAllNotes()
    suspend fun insert(note: Note) {
        notesDAO.insert(note)
    }
    suspend fun update(note: Note) {
        notesDAO.update(note)
    }
    suspend fun delete(note: Note) {
        notesDAO.delete(note)
    }
}