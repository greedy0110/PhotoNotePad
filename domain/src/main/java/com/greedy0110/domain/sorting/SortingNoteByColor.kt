package com.greedy0110.domain.sorting

import com.greedy0110.domain.ColorStore
import com.greedy0110.domain.Note

class SortingNoteByColor(
    private val colorStore: ColorStore
) : SortingNoteAlgorithm {
    override suspend fun sorted(source: List<Note>): List<Note> {
        val colorOrder = colorStore.getOrder()
        return source.sortedBy {
            when (val order = colorOrder.indexOf(it.color)) {
                -1 -> colorOrder.size + 1
                else -> order
            }
        }
    }
}