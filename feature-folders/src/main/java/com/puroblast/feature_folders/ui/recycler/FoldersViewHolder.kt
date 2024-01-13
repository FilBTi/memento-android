package com.puroblast.feature_folders.ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.puroblast.domain_memento.model.Folder
import com.puroblast.feature_folders.R
import com.puroblast.feature_folders.databinding.FolderItemBinding

class FoldersViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val binding by viewBinding(FolderItemBinding::bind)

    fun bind(item: Folder) {
        binding.folderText.text = item.name
        binding.folderImage.setImageResource(R.drawable.folder)
    }
}
