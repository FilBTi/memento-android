package com.puroblast.feature_folders.recycler

import androidx.recyclerview.widget.DiffUtil
import com.puroblast.feature_folders.recycler.model.FolderItem

class FoldersDiffUtil : DiffUtil.ItemCallback<FolderItem>() {

    override fun areItemsTheSame(oldItem: FolderItem, newItem: FolderItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FolderItem, newItem: FolderItem): Boolean {
        return oldItem.name == newItem.name
    }
}
