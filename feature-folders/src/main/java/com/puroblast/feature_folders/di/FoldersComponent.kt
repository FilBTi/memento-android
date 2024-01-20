package com.puroblast.feature_folders.di

import com.puroblast.feature_folders.ui.folders_screen.CreateFolderDialogFragment
import com.puroblast.feature_folders.ui.folders_screen.HomeFragment
import com.puroblast.feature_folders.ui.notes_screen.CreateNoteDialogFragment
import com.puroblast.feature_folders.ui.notes_screen.NotesFragment
import com.puroblast.feature_folders.ui.questions_screen.QuestionDetailsFragment
import com.puroblast.feature_folders.ui.questions_screen.QuestionsFragment
import com.puroblast.feature_folders.ui.questions_screen.RepeatQuestionsFragment
import dagger.Component

@Component(dependencies = [FoldersDependencies::class])
internal interface FoldersComponent {

    fun inject(fragment: HomeFragment)
    fun inject(fragment: CreateFolderDialogFragment)
    fun inject(fragment: NotesFragment)
    fun inject(fragment: CreateNoteDialogFragment)
    fun inject(fragment: QuestionsFragment)
    fun inject(fragment: QuestionDetailsFragment)
    fun inject(fragment: RepeatQuestionsFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(foldersDependencies: FoldersDependencies): Builder

        fun build(): FoldersComponent
    }
}
