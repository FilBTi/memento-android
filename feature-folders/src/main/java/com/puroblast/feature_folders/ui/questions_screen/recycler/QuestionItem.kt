package com.puroblast.feature_folders.ui.questions_screen.recycler

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.puroblast.feature_folders.R

@Suppress("DataClassShouldBeImmutable")
data class QuestionItem(
    val id: Int,
    val name: String
) : AbstractItem<QuestionsViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.question_item
    override val type: Int
        get() = R.id.questionItem

    override var identifier: Long = id.toLong()
    override fun getViewHolder(v: View): QuestionsViewHolder {
        return QuestionsViewHolder(v)
    }
}
