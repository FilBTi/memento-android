package com.puroblast.feature_folders.ui.questions_screen

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.domain_memento.model.Question
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.databinding.FragmentRepeatQuestionsBinding
import com.puroblast.feature_folders.di.FoldersComponentViewModel
import com.puroblast.feature_folders.presentation.questions.RepeatQuestionsViewModel
import dagger.Lazy
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class RepeatQuestionsFragment : Fragment(R.layout.fragment_repeat_questions) {

    private val binding by viewBinding(FragmentRepeatQuestionsBinding::bind)

    @Inject
    internal lateinit var repeatQuestionsViewModelFactory: Lazy<RepeatQuestionsViewModel.Factory>

    private val repeatQuestionsViewModel: RepeatQuestionsViewModel by viewModels {
        repeatQuestionsViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FoldersComponentViewModel>().foldersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteId = requireArguments().getInt("noteId")

        viewLifecycleOwner.lifecycleScope.launch {
            repeatQuestionsViewModel.loadQuestions(noteId).collectLatest { questions : List<Question> ->
                repeatQuestionsViewModel.updateScreen(questions, 0)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                repeatQuestionsViewModel.state.collect {uiState ->
                    if (uiState.questions.isNotEmpty()) {
                        val currentQuestionIndex = uiState.currentQuestionId
                        binding.questionTextView.text = uiState.questions[currentQuestionIndex].question
                        binding.progressIndicator.max = uiState.questions.size
                        binding.progressIndicator.setProgress(uiState.currentQuestionId + 1, true)

                        if (uiState.isAnswered) {
                            binding.questionInput.boxStrokeWidth = 2
                            binding.questionInput.clearFocus()
                            binding.questionInput.isErrorEnabled = true
                            binding.questionEditText.isEnabled = false
                            if (uiState.isAnswerIsRight) {
                                val greenColor = ColorStateList.valueOf(resources.getColor(
                                    com.puroblast.common_resources.R.color.green))
                                binding.questionInput.error = "Правильный ответ"
                                binding.questionInput.setErrorIconTintList(greenColor)
                                binding.questionInput.setErrorTextColor(greenColor)
                                binding.questionInput.boxStrokeErrorColor = greenColor
                            } else {
                                val redColor = ColorStateList.valueOf(resources.getColor(
                                    com.puroblast.common_resources.R.color.red))
                                binding.questionInput.error = "Неверный ответ"
                                binding.questionInput.setErrorIconTintList(redColor)
                                binding.questionInput.setErrorTextColor(redColor)
                                binding.questionInput.boxStrokeErrorColor = redColor
                                binding.rightAnswerTextView.text = uiState.questions[currentQuestionIndex].answer
                                binding.rightAnswerCardView.visibility = View.VISIBLE
                            }
                        }

                        binding.confirmButton.setOnClickListener {
                            if (uiState.isAnswered) {
                                if (uiState.isLastQuestion) {
                                    findNavController().popBackStack()
                                } else {
                                    binding.questionInput.boxStrokeWidth = 0
                                    binding.questionInput.isErrorEnabled = false
                                    binding.questionEditText.isEnabled = true
                                    binding.rightAnswerCardView.visibility = View.GONE
                                    repeatQuestionsViewModel.updateScreen(
                                        currentQuestionId = currentQuestionIndex + 1,
                                        isAnswered = false,
                                        isAnswerIsRight = true
                                    )
                                }
                            } else {
                                if (uiState.isLastQuestion) {
                                    binding.confirmButton.text = "Закончить повторение"
                                    repeatQuestionsViewModel.updateScreen(
                                        isAnswered = true,
                                        isAnswerIsRight = (binding.questionEditText.text.toString() == uiState.questions[currentQuestionIndex].answer)
                                    )
                                } else {
                                    binding.confirmButton.text = "Следующий вопрос"
                                    repeatQuestionsViewModel.updateScreen(
                                        isAnswered = true,
                                        isAnswerIsRight = (binding.questionEditText.text.toString() == uiState.questions[currentQuestionIndex].answer)
                                    )
                                }
                            }
                        }
                    }

                }
            }
        }

        binding.repeatQuestionToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

}
