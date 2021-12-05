package com.greedy0110.domain.usecase

import com.greedy0110.domain.ColorStore

class GetRandomColorUseCase(
    private val colorStore: ColorStore
) {

    suspend fun execute(): ULong {
        return colorStore.getOrder().random()
    }
}