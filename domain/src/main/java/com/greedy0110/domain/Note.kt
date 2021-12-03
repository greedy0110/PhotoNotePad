package com.greedy0110.domain

import java.time.LocalDate

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDate,
    val color: Color,
    val isPin: Boolean,
    val images: List<String>
) {
    data class Color(
        val id: Int,
        val code: Int
    )
}
