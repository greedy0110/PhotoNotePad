package com.greedy0110.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.greedy0110.domain.MemoryNoteStore
import com.greedy0110.domain.NoteStore
import com.greedy0110.domain.SampleNote
import com.greedy0110.domain.error.NotFoundEntityException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateSingleNoteUseCaseTest {

    lateinit var noteStore: NoteStore

    @Before
    fun setup() {
        noteStore = MemoryNoteStore()
    }

    @Test
    fun shouldUpdateANote() = runTest {
        val createUseCase = CreateSingleNoteUseCase(noteStore)
        val sample1 = SampleNote.all[0]
        createUseCase.execute(sample1)

        val updateUseCase = UpdateSingleNoteUseCase(noteStore)
        var savedNote = noteStore.getAll()[0]
        updateUseCase.execute(savedNote.copy(title = "update successfully."))

        savedNote = noteStore.getAll()[0]
        assertThat(savedNote.contentEqual(sample1.copy(title = "update successfully.")))
    }

    @Test
    fun shouldCatchNotFoundEntityWhenNoSuchEntityFounded() = runTest {
        val updateUseCase = UpdateSingleNoteUseCase(noteStore)
        val sample1 = SampleNote.all[0]
        assertThrows(NotFoundEntityException::class.java) {
            runBlocking { updateUseCase.execute(sample1) }
        }
    }
}