package com.puroblast.feature_folders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.puroblast.domain_memento.model.Folder
import com.puroblast.domain_memento.repository.FolderRepository
import com.puroblast.feature_folders.ui.recycler.FolderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FoldersViewModel(
    private val folderRepository: FolderRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<FolderItem>())
    val state = _state.asStateFlow()

    init {
        loadFolders()
    }

    private fun loadFolders() {
        viewModelScope.launch {
            folderRepository.observeFolders().collect {
                folders: List<Folder> ->
                val folderItems = folders.map { FolderItem(it.name) }
                _state.update { folderItems }
            }
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
