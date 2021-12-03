package com.greedy0110.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.greedy0110.domain.MemoryNoteStore
import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore
import com.greedy0110.domain.SampleNote
import com.greedy0110.domain.sorting.SortingNoteByColor
import com.greedy0110.domain.sorting.SortingNoteByCreatedAt
import com.greedy0110.domain.sorting.SortingNoteByDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllNotesUseCaseTest {

    lateinit var noteStore: NoteStore

    @Before
    fun setup() = runTest {
        noteStore = MemoryNoteStore()

    }

    @Test
    fun shouldGetAllDescendingOrderByCreatedAt() = runTest {
        val (sample1, sample2, sample3) = SampleNote.all.subList(0, 3)
        val createUseCase = CreateSingleNoteUseCase(noteStore)
        createUseCase.execute(sample1)
        createUseCase.execute(sample2)
        createUseCase.execute(sample3)

        val getAllUseCase = GetAllNotesUseCase(noteStore)
        val howToSort = SortingNoteByCreatedAt()
        val result = getAllUseCase.execute(howToSort)

        assertThat(result).hasSize(3)
        assertThat(result).isInOrder(
            Comparator<Note> { note1, note2 -> note2.createdAt.compareTo(note1.createdAt) }
        )
    }

    @Test
    fun shouldDescendingOrderByItsDate() = runTest {
        val base = SampleNote.all[0]
        val createUseCase = CreateSingleNoteUseCase(noteStore)
        createUseCase.execute(base.copy(date = LocalDate.of(2020, 12, 3)))
        createUseCase.execute(base.copy(date = LocalDate.of(2021, 1, 3)))
        createUseCase.execute(base.copy(date = LocalDate.of(2020, 11, 3)))
        createUseCase.execute(base.copy(date = null))

        val getAllUseCase = GetAllNotesUseCase(noteStore)
        val howToSort = SortingNoteByDate()
        val result = getAllUseCase.execute(howToSort)

        assertThat(result).hasSize(4)
        assertThat(result).isInOrder(
            Comparator<Note> { note1, note2 ->
                when {
                    note2.date == null && note1.date == null -> 0
                    note1.date == null -> 1
                    note2.date == null -> -1
                    else -> note2!!.date!!.compareTo(note1.date)
                }
            }
        )
    }

    @Test
    fun shouldOrderByColor() = runTest {
        val base = SampleNote.all[0]
        val createUseCase = CreateSingleNoteUseCase(noteStore)
        createUseCase.execute(base.copy(color = null))
        createUseCase.execute(base.copy(color = null))
        createUseCase.execute(base.copy(color = null))
        createUseCase.execute(base.copy(color = 1))
        createUseCase.execute(base.copy(color = 2))
        createUseCase.execute(base.copy(color = 1))
        createUseCase.execute(base.copy(color = 3))
        createUseCase.execute(base.copy(color = 1))
        createUseCase.execute(base.copy(color = 2))
        val colorPriorities = listOf(2, 3, 1)

        val getAllUseCas = GetAllNotesUseCase(noteStore)
        val howToSort = SortingNoteByColor(colorPriorities)
        val result = getAllUseCas.execute(howToSort)

        assertThat(result).hasSize(9)
        assertThat(result.map { it.color })
            .isEqualTo(listOf(2, 2, 3, 1, 1, 1, null, null, null))
    }
}