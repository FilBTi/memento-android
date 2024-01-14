package com.puroblast.feature_folders.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.puroblast.domain_memento.model.Folder
import com.puroblast.feature_folders.R

internal class FoldersAdapter : ListAdapter<Folder, FoldersViewHolder>(FoldersDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoldersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder_item, parent, false)

        return FoldersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: FoldersViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
