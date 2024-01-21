package com.puroblast.domain_memento.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val question: String,
    val answer: String,
    val noteId: Int
)
