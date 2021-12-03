package com.greedy0110.domain.usecase

import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore
import com.greedy0110.domain.error.NotFoundEntityException

class UpdateSingleNoteUseCase(
    private val noteStore: NoteStore
) {

    @Throws(NotFoundEntityException::class)
    suspend fun execute(note: Note) {
        noteStore.update(note)
    }
}