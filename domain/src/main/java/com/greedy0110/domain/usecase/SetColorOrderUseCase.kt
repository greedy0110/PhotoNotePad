package com.greedy0110.domain.usecase

import com.greedy0110.domain.ColorStore

class SetColorOrderUseCase(
    private val colorStore: ColorStore
) {

    suspend fun execute(newOrder: List<ULong>) {
        colorStore.saveOrder(newOrder)
    }
}