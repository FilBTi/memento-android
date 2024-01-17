package com.puroblast.domain_memento.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.puroblast.domain_memento.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateQuestion(question: Question)

    @Query("SELECT * FROM question WHERE id = :id")
    suspend fun getQuestion(id: Int): Question

    @Query("SELECT * FROM question WHERE noteId = :noteId")
    fun getQuestions(noteId: Int): Flow<List<Question>>
}
