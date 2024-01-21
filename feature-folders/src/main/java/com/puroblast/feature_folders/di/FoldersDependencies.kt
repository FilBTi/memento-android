package com.puroblast.feature_folders.di

import com.puroblast.domain_memento.repository.FolderRepository
import com.puroblast.domain_memento.repository.NoteRepository
import com.puroblast.domain_memento.repository.QuestionRepository

interface FoldersDependencies {
    val folderRepository: FolderRepository
    val noteRepository: NoteRepository
    val questionRepository: QuestionRepository
}
