package com.greedy0110.domain

import com.greedy0110.domain.error.NotFoundEntityException

interface NoteStore {
    suspend fun getAll(): List<Note>
    suspend fun add(note: Note)

    @Throws(NotFoundEntityException::class)
    suspend fun update(note: Note)
}