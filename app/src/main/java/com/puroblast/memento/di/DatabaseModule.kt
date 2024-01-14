package com.puroblast.memento.di

import android.app.Application
import androidx.room.Room
import com.puroblast.memento.db.MementoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMementoDatabase(application: Application): MementoDatabase {
        val database = Room.databaseBuilder(
            application,
            MementoDatabase::class.java,
            "memento_db"
        ).build()
        return database
    }
}
