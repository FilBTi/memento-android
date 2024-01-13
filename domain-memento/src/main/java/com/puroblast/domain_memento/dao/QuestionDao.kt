package com.puroblast.domain_memento.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puroblast.domain_memento.model.Question
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("UPDATE question SET question=:question AND answer=:answer WHERE id=:id")
    suspend fun updateQuestion(id: Int, question: String, answer: String)

    @Query("SELECT * FROM question WHERE id = :id")
    suspend fun getQuestion(id: Int): Question

    @Query("SELECT * FROM question WHERE id IN(:ids)")
    fun getNoteQuestions(ids: List<Int>): Flow<List<Question>>
}
