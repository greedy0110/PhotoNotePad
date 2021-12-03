package com.greedy0110.domain.sorting

import com.greedy0110.domain.Note

class SortingNoteByColor(
    private val colorPriorities: List<Int>
) : SortingNoteAlgorithm {
    override fun sorted(source: List<Note>): List<Note> {
        return source.sortedBy {
            when (val order = colorPriorities.indexOf(it.color)) {
                -1 -> colorPriorities.size + 1
                else -> order
            }
        }
    }
}