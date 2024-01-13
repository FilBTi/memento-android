package com.puroblast.feature_folders.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import com.puroblast.domain_memento.model.Folder

class FoldersDiffUtil : DiffUtil.ItemCallback<Folder>() {

    override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
        return oldItem.name == newItem.name
    }
}
