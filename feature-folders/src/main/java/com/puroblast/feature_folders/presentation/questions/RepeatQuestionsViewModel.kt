package com.puroblast.feature_folders.presentation.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Question
import com.puroblast.domain_memento.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepeatQuestionsViewModel(
    private val questionRepository: QuestionRepository
): ViewModel() {

    private val _state = MutableStateFlow(RepeatQuestionUiState())
    val state = _state.asStateFlow()

    fun loadQuestions(noteId : Int) : Flow<List<Question>> {
        return questionRepository.observeQuestions(noteId)
    }

    fun updateScreen(
        questions : List<Question> = _state.value.questions,
        currentQuestionId : Int = _state.value.currentQuestionId,
        isAnswered : Boolean = _state.value.isAnswered,
        isAnswerIsRight : Boolean = _state.value.isAnswerIsRight
    ) {
        _state.value = RepeatQuestionUiState(
            questions,
            currentQuestionId,
            isAnswered,
            isAnswerIsRight
        )
    }

    class Factory @Inject constructor(
        private val questionRepository: QuestionRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == RepeatQuestionsViewModel::class.java)
            return RepeatQuestionsViewModel(questionRepository) as T
        }
    }
}
