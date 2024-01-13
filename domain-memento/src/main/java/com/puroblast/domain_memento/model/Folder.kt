package com.puroblast.domain_memento.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Folder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val notesIds: List<Int> = listOf()
)
