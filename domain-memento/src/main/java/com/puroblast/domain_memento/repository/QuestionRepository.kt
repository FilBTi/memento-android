package com.puroblast.domain_memento.repository

import com.puroblast.domain_memento.dao.QuestionDao
import com.puroblast.domain_memento.model.Question

class QuestionRepository(private val questionDao: QuestionDao) {

    suspend fun createQuestion(question: Question) {
        questionDao.createQuestion(question)
    }

    suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question)
    }

    suspend fun updateQuestion(id: Int, question: String, answer: String) {
        questionDao.updateQuestion(id, question, answer)
    }

    suspend fun getQuestion(id: Int): Question {
        return questionDao.getQuestion(id)
    }
}
