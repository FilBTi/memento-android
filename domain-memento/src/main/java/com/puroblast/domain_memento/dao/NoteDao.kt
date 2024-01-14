package com.puroblast.domain_memento.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNoteQuestions(note: Note)

    @Query("SELECT * FROM note WHERE id IN(:ids)")
    fun getNotes(ids: List<Int>): Flow<List<Note>>
}
