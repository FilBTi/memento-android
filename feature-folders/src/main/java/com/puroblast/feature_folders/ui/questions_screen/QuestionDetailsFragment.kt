package com.puroblast.feature_folders.ui.questions_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.domain_memento.model.Question
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.databinding.FragmentQuestionDetailsBinding
import com.puroblast.feature_folders.di.FoldersComponentViewModel
import com.puroblast.feature_folders.presentation.questions.QuestionDetailsViewModel
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionDetailsFragment : Fragment(R.layout.fragment_question_details) {

    private val binding by viewBinding(FragmentQuestionDetailsBinding::bind)

    @Inject
    internal lateinit var questionDetailsViewModelFactory: Lazy<QuestionDetailsViewModel.Factory>

    private val questionDetailsViewModel: QuestionDetailsViewModel by viewModels {
        questionDetailsViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FoldersComponentViewModel>().foldersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteId = requireArguments().getInt("noteId")
        val isExist = requireArguments().getBoolean("isExist")

        if (isExist) {
            viewLifecycleOwner.lifecycleScope.launch {
                val questionId = requireArguments().getInt("questionId")
                val question = questionDetailsViewModel.loadQuestion(questionId)

                binding.questionEditText.setText(question.question)
                binding.answerEditText.setText(question.answer)
                binding.questionDetailsToolbar.title = "Редактировать вопрос"

                binding.questionDetailsToolbar.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.saveButton -> {
                            questionDetailsViewModel.updateQuestion(
                                Question(
                                    question.id,
                                    binding.questionEditText.text.toString(),
                                    binding.answerEditText.text.toString(),
                                    question.noteId
                                )
                            )
                            findNavController().popBackStack()
                            true
                        }

                        else -> false
                    }
                }
            }
        } else {
            binding.questionDetailsToolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.saveButton -> {
                        questionDetailsViewModel.addQuestion(
                            Question(
                                0,
                                binding.questionEditText.text.toString(),
                                binding.answerEditText.text.toString(),
                                noteId
                            )
                        )
                        findNavController().popBackStack()
                        true
                    }

                    else -> false
                }
            }
        }

        binding.questionDetailsToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
