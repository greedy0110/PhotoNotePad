package com.greedy0110.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.greedy0110.domain.MemoryNoteStore
import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class CreateSingleNoteUseCaseTest {

    @Test
    fun shouldCreateANote() = runTest {
        val noteStore: NoteStore = MemoryNoteStore()
        val useCase = CreateSingleNoteUseCase(noteStore)
        useCase.execute(sampleNote1)

        val result = noteStore.getAll()
        assertThat(result).hasSize(1)
        // "id property can be changed by implementation of NoteStore.
        assertThat(result[0].copy(id = 9999)).isEqualTo(sampleNote1)
    }

    private val sampleNote1 = Note(
        id = 9999,
        title = "very first note",
        content = "hello photo note pads",
        date = LocalDate.of(2021, 12, 3),
        color = Note.Color(1, 0xffffff),
        isPin = false,
        images = emptyList()
    )
}
