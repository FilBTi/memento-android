package com.puroblast.feature_folders.ui.folders_screen.recycler

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.puroblast.feature_folders.R

class FolderItem(
    val folderName: String,
    val id: Int
) : AbstractItem<FoldersViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.folder_item
    override val type: Int
        get() = R.id.folderItem

    override var identifier: Long = id.toLong()
    override fun getViewHolder(v: View): FoldersViewHolder {
        return FoldersViewHolder(v)
    }
}
