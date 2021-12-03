package com.greedy0110.domain.sorting

import com.greedy0110.domain.Note

class SortingNoteByCreatedAt : SortingNoteAlgorithm {
    override fun sorted(source: List<Note>): List<Note> {
        return source.sortedByDescending { it.createdAt }
    }
}