package com.puroblast.domain_memento.repository

import com.puroblast.domain_memento.dao.QuestionDao
import com.puroblast.domain_memento.model.Question
import kotlinx.coroutines.flow.Flow

class QuestionRepository(private val questionDao: QuestionDao) {

    suspend fun createQuestion(question: Question) {
        questionDao.createQuestion(question)
    }

    suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question)
    }

    suspend fun updateQuestion(question: Question) {
        questionDao.updateQuestion(question)
    }

    suspend fun getQuestion(id: Int): Question {
        return questionDao.getQuestion(id)
    }

    fun observeQuestions(ids: List<Int>): Flow<List<Question>> {
        return questionDao.getNoteQuestions(ids)
    }
}
