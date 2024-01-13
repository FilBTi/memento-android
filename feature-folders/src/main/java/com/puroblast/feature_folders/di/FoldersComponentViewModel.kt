package com.puroblast.feature_folders.di

import androidx.lifecycle.ViewModel

internal class FoldersComponentViewModel : ViewModel() {

    val foldersComponent = DaggerFoldersComponent.builder()
            .dependencies(FoldersDependenciesProvider.dependencies)
            .build()
}
