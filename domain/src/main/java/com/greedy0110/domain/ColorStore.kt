package com.greedy0110.domain

interface ColorStore {
    suspend fun getOrder(): List<Int>
    suspend fun saveOrder(newOrder: List<Int>)
}