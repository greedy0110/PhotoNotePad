package com.greedy0110.local

import com.greedy0110.domain.ColorStore

class MemoryColorStore : ColorStore {
    private var order = listOf(2, 3, 1)

    override suspend fun getOrder(): List<Int> {
        return order
    }

    override suspend fun saveOrder(newOrder: List<Int>) {
        order = newOrder
    }
}