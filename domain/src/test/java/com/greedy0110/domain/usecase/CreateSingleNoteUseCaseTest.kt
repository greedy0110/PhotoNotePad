package com.greedy0110.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.greedy0110.domain.MemoryNoteStore
import com.greedy0110.domain.NoteStore
import com.greedy0110.domain.SampleNote
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CreateSingleNoteUseCaseTest {

    @Test
    fun shouldCreateANote() = runTest {
        val noteStore: NoteStore = MemoryNoteStore()
        val useCase = CreateSingleNoteUseCase(noteStore)
        val sampleNote1 = SampleNote.all[0]
        useCase.execute(sampleNote1)

        val result = noteStore.getAll()
        assertThat(result).hasSize(1)
        // "id property can be changed by implementation of NoteStore.
        assertThat(result[0].copy(id = 9999)).isEqualTo(sampleNote1.copy(id = 9999))
    }
}
