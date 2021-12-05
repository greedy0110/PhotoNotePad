package com.greedy0110.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.greedy0110.domain.ColorStore
import com.greedy0110.domain.MemoryColorStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SetColorOrderUseCaseTest {

    lateinit var colorStore: ColorStore

    @Before
    fun setup() {
        colorStore = MemoryColorStore()
    }

    @Test
    fun shouldReplaceNewColorOrder() = runTest {
        val newOrder = listOf<ULong>(2u, 3u, 1u)
        val useCase = SetColorOrderUseCase(colorStore)
        useCase.execute(newOrder)

        assertThat(colorStore.getOrder()).isEqualTo(newOrder)
    }
}