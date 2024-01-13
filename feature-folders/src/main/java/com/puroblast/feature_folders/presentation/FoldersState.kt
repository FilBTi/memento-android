package com.puroblast.feature_folders.presentation

import com.puroblast.domain_memento.model.Folder

data class FoldersState(
    val folders: List<Folder> = emptyList()
)
