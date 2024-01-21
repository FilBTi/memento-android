package com.puroblast.memento.di

import com.puroblast.domain_memento.dao.FolderDao
import com.puroblast.domain_memento.dao.NoteDao
import com.puroblast.domain_memento.dao.QuestionDao
import com.puroblast.domain_memento.repository.FolderRepository
import com.puroblast.domain_memento.repository.NoteRepository
import com.puroblast.domain_memento.repository.QuestionRepository
import com.puroblast.memento.db.MementoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideFolderRepository(folderDao: FolderDao): FolderRepository {
        return FolderRepository(folderDao)
    }

    @Provides
    @Singleton
    fun provideFolderDao(db: MementoDatabase): FolderDao {
        return db.folderDao
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteDao(db: MementoDatabase): NoteDao {
        return db.noteDao
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(questionDao: QuestionDao): QuestionRepository {
        return QuestionRepository(questionDao)
    }

    @Provides
    @Singleton
    fun provideQuestionDao(db: MementoDatabase): QuestionDao {
        return db.questionDao
    }
}
