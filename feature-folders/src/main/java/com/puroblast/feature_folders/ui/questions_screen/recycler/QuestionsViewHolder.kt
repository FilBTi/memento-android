package com.puroblast.feature_folders.ui.questions_screen.recycler

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.puroblast.feature_folders.databinding.QuestionItemBinding

class QuestionsViewHolder(private val view: View) : FastAdapter.ViewHolder<QuestionItem>(view) {

    private val binding by viewBinding(QuestionItemBinding::bind)
    override fun bindView(item: QuestionItem, payloads: List<Any>) {
        binding.questionText.text = item.name
    }

    override fun unbindView(item: QuestionItem) {
        binding.questionText.text = null
    }
}
