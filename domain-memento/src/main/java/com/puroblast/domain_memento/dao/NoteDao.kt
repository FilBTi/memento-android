package com.puroblast.domain_memento.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puroblast.domain_memento.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNote(id: Int): Note

    @Query("UPDATE note SET questionsIds = :questionsIds WHERE id = :id")
    suspend fun updateNoteQuestions(id: Int, questionsIds: List<Int>)

    @Query("SELECT * FROM note WHERE id IN(:ids)")
    fun getNotes(ids: List<Int>): Flow<List<Note>>
}
