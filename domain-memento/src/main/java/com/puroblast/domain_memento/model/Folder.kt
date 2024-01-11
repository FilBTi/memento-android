package com.puroblast.domain_memento.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Folder(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val childFoldersIds: List<Int>,
    val notesIds: List<Int>
)
