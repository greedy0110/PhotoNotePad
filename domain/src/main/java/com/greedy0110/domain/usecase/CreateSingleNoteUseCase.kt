package com.greedy0110.domain.usecase

import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore

class CreateSingleNoteUseCase(
    private val noteStore: NoteStore
) {

    suspend fun execute(note: Note) {
        noteStore.add(note)
    }
}