package com.greedy0110.domain.usecase

import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore

class GetAllNotesUseCase(
    private val noteStore: NoteStore
) {

    suspend fun execute(): List<Note> {
        val notes = noteStore.getAll()
        return notes.sortedByDescending { it.createdAt }
    }
}