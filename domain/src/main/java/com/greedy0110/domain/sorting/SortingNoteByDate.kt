package com.greedy0110.domain.sorting

import com.greedy0110.domain.Note

class SortingNoteByDate : SortingNoteAlgorithm {
    override suspend fun sorted(source: List<Note>): List<Note> {
        return source.sortedByDescending { it.date }
    }
}