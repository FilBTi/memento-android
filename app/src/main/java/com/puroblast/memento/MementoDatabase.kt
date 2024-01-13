package com.puroblast.memento

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.puroblast.domain_memento.converters.FolderConverter
import com.puroblast.domain_memento.dao.FolderDao
import com.puroblast.domain_memento.dao.NoteDao
import com.puroblast.domain_memento.dao.QuestionDao
import com.puroblast.domain_memento.model.Folder
import com.puroblast.domain_memento.model.Note
import com.puroblast.domain_memento.model.Question

@Database(version = 1, entities = [Folder::class, Note::class, Question::class])
@TypeConverters(FolderConverter::class)
abstract class MementoDatabase : RoomDatabase() {

    abstract val folderDao: FolderDao
    abstract val noteDao: NoteDao
    abstract val questionDao: QuestionDao
}
