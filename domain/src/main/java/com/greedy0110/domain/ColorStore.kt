package com.greedy0110.domain

interface ColorStore {
    suspend fun getOrder(): List<ULong>
    suspend fun saveOrder(newOrder: List<ULong>)
}