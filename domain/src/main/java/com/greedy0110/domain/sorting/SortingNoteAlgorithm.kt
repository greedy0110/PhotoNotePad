package com.greedy0110.domain.sorting

import com.greedy0110.domain.Note

interface SortingNoteAlgorithm {
    fun sorted(source: List<Note>): List<Note>
}