package com.puroblast.feature_folders.di

import com.puroblast.feature_folders.HomeFragment
import dagger.Component

@Component(dependencies = [FoldersDependencies::class])
internal interface FoldersComponent {

    fun inject(fragment: HomeFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(foldersDependencies: FoldersDependencies): Builder

        fun build(): FoldersComponent
    }
}
