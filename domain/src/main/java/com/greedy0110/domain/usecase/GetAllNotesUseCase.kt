package com.greedy0110.domain.usecase

import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore
import com.greedy0110.domain.sorting.SortingNoteAlgorithm
import com.greedy0110.domain.sorting.SortingNoteByCreatedAt

class GetAllNotesUseCase(
    private val noteStore: NoteStore
) {

    suspend fun execute(
        howToSort: SortingNoteAlgorithm = SortingNoteByCreatedAt()
    ): List<Note> {
        val notes = noteStore.getAll()
        return howToSort.sorted(notes)
    }
}

