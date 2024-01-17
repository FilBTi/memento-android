package com.puroblast.feature_folders.presentation.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Question
import com.puroblast.domain_memento.repository.QuestionRepository
import com.puroblast.feature_folders.ui.questions_screen.recycler.QuestionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsViewModel(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<QuestionItem>())
    val state = _state.asStateFlow()

    fun loadQuestions(noteId: Int) {
        viewModelScope.launch {
            questionRepository.observeQuestions(noteId).collect {
                questions: List<Question> ->
                val questionItems = questions.map { QuestionItem(it.id, it.question) }
                _state.value = questionItems
            }
        }
    }

    class Factory @Inject constructor(
        private val questionRepository: QuestionRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == QuestionsViewModel::class.java)
            return QuestionsViewModel(questionRepository) as T
        }
    }
}
