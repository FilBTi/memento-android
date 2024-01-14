package com.puroblast.feature_folders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Folder
import com.puroblast.domain_memento.repository.FolderRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateFolderDialogViewModel(
    private val folderRepository: FolderRepository
) : ViewModel() {

    fun addFolder(folder: Folder) {
        viewModelScope.launch {
            folderRepository.createFolder(folder)
        }
    }

    class Factory @Inject constructor(
        private val folderRepository: FolderRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            require(modelClass == CreateFolderDialogViewModel::class.java)
            return CreateFolderDialogViewModel(folderRepository) as T
        }
    }
}
