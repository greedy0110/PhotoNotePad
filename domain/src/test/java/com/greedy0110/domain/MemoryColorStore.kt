package com.greedy0110.domain

class MemoryColorStore : ColorStore {
    private var order = listOf<ULong>(2u, 3u, 1u)

    override suspend fun getOrder(): List<ULong> {
        return order
    }

    override suspend fun saveOrder(newOrder: List<ULong>) {
        order = newOrder
    }
}