package com.puroblast.memento.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.puroblast.domain_memento.dao.FolderDao
import com.puroblast.domain_memento.dao.NoteDao
import com.puroblast.domain_memento.dao.QuestionDao
import com.puroblast.domain_memento.repository.FolderRepository
import com.puroblast.domain_memento.repository.NoteRepository
import com.puroblast.domain_memento.repository.QuestionRepository
import com.puroblast.memento.MementoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideMementoDatabase(context: Context): MementoDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MementoDatabase::class.java,
            "memento_db"
        ).build()
    }

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
