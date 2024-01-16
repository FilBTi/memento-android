package com.puroblast.feature_folders.ui.notes_screen.recycler

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.puroblast.feature_folders.R

class NoteItem(
    val id: Int,
    val noteName: String
) : AbstractItem<NotesViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.note_item
    override val type: Int
        get() = R.id.noteItem

    override var identifier: Long = id.toLong()
    override fun getViewHolder(v: View): NotesViewHolder {
        return NotesViewHolder(v)
    }
}
