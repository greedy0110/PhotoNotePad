package com.greedy0110.domain

import java.time.LocalDate

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDate?,
    val color: ULong,
    val isPin: Boolean,
    val images: List<String>,
    val createdAt: Long
) {

    fun contentEqual(other: Note): Boolean {
        return title == other.title &&
                content == other.content &&
                date == other.date &&
                color == other.color &&
                images == other.images
    }
}
