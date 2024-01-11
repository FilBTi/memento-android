package com.puroblast.domain_memento.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puroblast.domain_memento.model.Folder
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createFolder(folder: Folder)

    @Delete
    suspend fun deleteFolder(folder: Folder)

    @Query("SELECT * FROM folder WHERE id = :id")
    suspend fun getFolder(id: Int): Folder

    @Query("UPDATE folder SET childFoldersIds = :childFolderIds WHERE id = :id")
    suspend fun updateChildFolders(id: Int, childFolderIds: List<Int>)

    @Query("UPDATE folder SET notesIds = :notesIds WHERE id=:id")
    suspend fun updateFolderNotes(id: Int, notesIds: List<Int>)

    @Query("SELECT * FROM folder WHERE id IN(:ids)")
    fun getFolders(ids: List<Int>): Flow<List<Folder>>
}
