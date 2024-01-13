package com.puroblast.feature_folders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.repository.FolderRepository
import javax.inject.Inject

class FoldersViewModel(
    private val folderRepository: FolderRepository
) : ViewModel() {

    class Factory @Inject constructor(
        private val folderRepository: FolderRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == FoldersViewModel::class.java)
            return FoldersViewModel(folderRepository) as T
        }
    }
}
