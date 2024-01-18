package com.puroblast.feature_folders.ui.questions_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.select.selectExtension
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.databinding.FragmentQuestionsBinding
import com.puroblast.feature_folders.di.FoldersComponentViewModel
import com.puroblast.feature_folders.presentation.questions.QuestionsViewModel
import com.puroblast.feature_folders.ui.questions_screen.recycler.QuestionItem
import dagger.Lazy
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    private val binding by viewBinding(FragmentQuestionsBinding::bind)

    @Inject
    internal lateinit var questionsViewModelFactory: Lazy<QuestionsViewModel.Factory>

    private val questionsViewModel: QuestionsViewModel by viewModels {
        questionsViewModelFactory.get()
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get<FoldersComponentViewModel>().foldersComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val noteId = requireArguments().getInt("noteId")
        binding.questionsToolbar.title = requireArguments().getString("noteName")

        questionsViewModel.loadQuestions(noteId)

        val questionItemAdapter = FastItemAdapter<QuestionItem>()
        questionItemAdapter.selectExtension {
            isSelectable = true
            multiSelect = true
            selectOnLongClick = true
        }

        binding.questionsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.questionsRecyclerView.adapter = questionItemAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                questionsViewModel.state.collect { state ->
                    val result = FastAdapterDiffUtil.calculateDiff(questionItemAdapter.itemAdapter, state)
                    FastAdapterDiffUtil[questionItemAdapter.itemAdapter] = result
                }
            }
        }

        binding.addButton.setOnClickListener {
            val bundle = bundleOf()
            bundle.putInt("noteId", noteId)
            bundle.putBoolean("isExist", false)
            findNavController().navigate(R.id.action_questionsFragment_to_questionDetailsFragment, bundle)
        }

        questionItemAdapter.onClickListener = { view, adapter, item, position ->
            val bundle = bundleOf()
            bundle.putInt("noteId", noteId)
            bundle.putInt("questionId", item.id)
            bundle.putBoolean("isExist", true)
            findNavController().navigate(R.id.action_questionsFragment_to_questionDetailsFragment, bundle)
            true
        }

        binding.questionsToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
