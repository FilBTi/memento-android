package com.puroblast.feature_folders.ui.recycler

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.puroblast.feature_folders.R

class FolderItem(
    val folderName: String
) : AbstractItem<FoldersViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.folder_item
    override val type: Int
        get() = R.id.folderItem

    override fun getViewHolder(v: View): FoldersViewHolder {
        return FoldersViewHolder(v)
    }
}
