package com.greedy0110.domain

import java.time.LocalDate

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color,
    val date: LocalDate?,
    val isPin: Boolean,
    val images: List<String>,
    val createdAt: Long
) {
    data class Color(
        val id: Int,
        val code: Int
    )
}
