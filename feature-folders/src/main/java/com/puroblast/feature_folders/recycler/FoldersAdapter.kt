package com.puroblast.feature_folders.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.recycler.model.FolderItem

class FoldersAdapter : ListAdapter<FolderItem, FoldersViewHolder>(FoldersDiffUtil()) {
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
