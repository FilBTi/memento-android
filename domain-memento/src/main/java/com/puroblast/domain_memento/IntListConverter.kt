package com.puroblast.domain_memento

import androidx.room.TypeConverter

class IntListConverter {

    @TypeConverter
    fun convertIntListToString(ids: List<Int>): String {
        return ids.joinToString(",")
    }

    @TypeConverter
    fun convertStringToIntList(ids: String): List<Int> {
        return if (ids.isNotEmpty()) {
            ids.split(",").map { it.toInt() }
        } else {
            emptyList()
        }
    }
}
