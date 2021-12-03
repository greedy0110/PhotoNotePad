package com.greedy0110.domain

interface NoteStore {
    suspend fun getAll(): List<Note>
    suspend fun add(note: Note)
}