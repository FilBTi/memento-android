package com.puroblast.feature_folders.di

import androidx.annotation.RestrictTo
import kotlin.properties.Delegates

interface FoldersDependenciesProvider {

    companion object : FoldersDependenciesProvider by FoldersDependenciesStore

    @get: RestrictTo(RestrictTo.Scope.LIBRARY)
    val dependencies: FoldersDependencies
}

object FoldersDependenciesStore : FoldersDependenciesProvider {

    override var dependencies: FoldersDependencies by Delegates.notNull()
}
