package com.puroblast.feature_folders.ui.folders_screen.recycler

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.puroblast.feature_folders.databinding.FolderItemBinding

class FoldersViewHolder(private val view: View) : FastAdapter.ViewHolder<FolderItem>(view) {

    private val binding by viewBinding(FolderItemBinding::bind)

    override fun bindView(item: FolderItem, payloads: List<Any>) {
        binding.folderText.text = item.folderName
    }

    override fun unbindView(item: FolderItem) {
        binding.folderText.text = null
    }
}
