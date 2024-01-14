package com.puroblast.feature_folders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Folder
import com.puroblast.domain_memento.repository.FolderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoldersViewModel(
    private val folderRepository: FolderRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Folder>())
    val state = _state.asStateFlow()

    init {
        loadFolders()
    }

    private fun loadFolders() {
        viewModelScope.launch {
            folderRepository.observeFolders().collect {
                folders: List<Folder> ->
                _state.update { folders }
            }
        }
    }

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
            require(modelClass == FoldersViewModel::class.java)
            return FoldersViewModel(folderRepository) as T
        }
    }
}
