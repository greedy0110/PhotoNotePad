package com.greedy0110.domain.usecase

import com.greedy0110.domain.ColorStore

class GetColorOrderUseCase(
    private val colorStore: ColorStore
) {

    suspend fun execute(): List<Int> {
        return colorStore.getOrder()
    }
}