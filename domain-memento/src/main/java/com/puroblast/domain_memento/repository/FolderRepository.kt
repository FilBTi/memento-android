package com.puroblast.domain_memento.repository

import com.puroblast.domain_memento.dao.FolderDao
import com.puroblast.domain_memento.model.Folder
import kotlinx.coroutines.flow.Flow

class FolderRepository(private val folderDao: FolderDao) {

    suspend fun createFolder(folder: Folder) {
        folderDao.createFolder(folder)
    }

    suspend fun deleteFolder(folder: Folder) {
        folderDao.deleteFolder(folder)
    }

    suspend fun getFolder(id: Int): Folder {
        return folderDao.getFolder(id)
    }

    suspend fun updateChildFolders(id: Int, childFolderIds: List<Int>) {
        folderDao.updateChildFolders(id, childFolderIds)
    }

    suspend fun updateFolderNotes(id: Int, notesIds: List<Int>) {
        folderDao.updateFolderNotes(id, notesIds)
    }

    fun getFolders(ids: List<Int>): Flow<List<Folder>> {
        return folderDao.getFolders(ids)
    }
}
