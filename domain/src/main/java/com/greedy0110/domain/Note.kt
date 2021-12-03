package com.greedy0110.domain

import java.time.LocalDate

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val date: LocalDate?,
    val color: Int?,
    val isPin: Boolean,
    val images: List<String>,
    val createdAt: Long
)
