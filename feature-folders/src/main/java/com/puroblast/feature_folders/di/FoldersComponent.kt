package com.puroblast.feature_folders.di

import com.puroblast.feature_folders.ui.folders_screen.CreateFolderDialogFragment
import com.puroblast.feature_folders.ui.folders_screen.HomeFragment
import dagger.Component

@Component(dependencies = [FoldersDependencies::class])
internal interface FoldersComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: CreateFolderDialogFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(foldersDependencies: FoldersDependencies): Builder

        fun build(): FoldersComponent
    }
}
