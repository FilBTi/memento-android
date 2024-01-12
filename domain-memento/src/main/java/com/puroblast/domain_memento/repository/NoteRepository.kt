package com.puroblast.domain_memento.repository

import com.puroblast.domain_memento.dao.NoteDao
import com.puroblast.domain_memento.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun createNote(note: Note) {
        noteDao.createNote(note)
    }

    suspend fun getNote(id: Int): Note {
        return noteDao.getNote(id)
    }

    suspend fun updateNoteQuestions(id: Int, questionsIds: List<Int>) {
        noteDao.updateNoteQuestions(id, questionsIds)
    }

    fun getNotes(ids: List<Int>): Flow<List<Note>> {
        return noteDao.getNotes(ids)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
}
