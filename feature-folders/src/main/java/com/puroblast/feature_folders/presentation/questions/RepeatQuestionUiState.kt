package com.puroblast.feature_folders.presentation.questions

import com.puroblast.domain_memento.model.Question

data class RepeatQuestionUiState(
    val questions : List<Question> = emptyList(),
    val currentQuestionId : Int = 0,
    val isAnswered : Boolean = false,
    val isAnswerIsRight : Boolean = true,
    val isLastQuestion : Boolean = questions.size == currentQuestionId + 1
)
