package com.puroblast.domain_memento.converters

import androidx.room.TypeConverter

class FolderConverter {

    @TypeConverter
    fun notesIdsToString(notesIds: List<Int>): String {
        return notesIds.joinToString(separator = ",")
    }

    @TypeConverter
    fun stringToNotesIds(ids: String): List<Int> {
        return if (ids != "") {
            ids.split(",").map { it.toInt() }
        } else { emptyList() }
    }
}
