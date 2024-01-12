package com.puroblast.feature_folders.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.feature_folders.databinding.FolderItemBinding
import com.puroblast.feature_folders.recycler.model.FolderItem

class FoldersViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding by viewBinding(FolderItemBinding::bind)

    fun bind(item: FolderItem) {
        binding.folderText.text = item.name
        binding.folderImage.setImageResource(item.image)
    }
}
