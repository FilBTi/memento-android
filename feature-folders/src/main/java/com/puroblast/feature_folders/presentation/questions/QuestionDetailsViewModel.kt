package com.puroblast.feature_folders.presentation.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Question
import com.puroblast.domain_memento.repository.QuestionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionDetailsViewModel(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    fun addQuestion(question: Question) {
        viewModelScope.launch {
            questionRepository.createQuestion(question)
        }
    }

    fun updateQuestion(question: Question) {
        viewModelScope.launch {
            questionRepository.updateQuestion(question)
        }
    }

    suspend fun loadQuestion(id: Int): Question {
        return questionRepository.getQuestion(id)
    }

    class Factory @Inject constructor(
        private val questionRepository: QuestionRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == QuestionDetailsViewModel::class.java)
            return QuestionDetailsViewModel(questionRepository) as T
        }
    }
}
