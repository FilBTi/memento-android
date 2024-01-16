package com.puroblast.feature_folders.ui.notes_screen.recycler

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.puroblast.feature_folders.databinding.NoteItemBinding

class NotesViewHolder(private val view: View) : FastAdapter.ViewHolder<NoteItem>(view) {

    private val binding by viewBinding(NoteItemBinding::bind)
    override fun bindView(item: NoteItem, payloads: List<Any>) {
        binding.noteText.text = item.noteName
    }

    override fun unbindView(item: NoteItem) {
        binding.noteText.text = null
    }
}
