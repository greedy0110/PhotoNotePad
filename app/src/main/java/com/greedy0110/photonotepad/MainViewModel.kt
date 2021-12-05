package com.greedy0110.photonotepad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore
import com.greedy0110.domain.usecase.CreateSingleNoteUseCase
import com.greedy0110.domain.usecase.GetAllNotesUseCase
import com.greedy0110.local.MemoryNoteStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel : ViewModel() {

    private val noteStore: NoteStore = MemoryNoteStore()

    private val _notes: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private var cnt = 0
    fun addRandomNote() {
        viewModelScope.launch {
            val createUseCase = CreateSingleNoteUseCase(noteStore)
            createUseCase.execute(generateNote(cnt++))

            val getAllNotesUseCase = GetAllNotesUseCase(noteStore)
            _notes.value = getAllNotesUseCase.execute()
        }
    }

    private fun generateNote(seed: Int): Note {
        return Note(
            id = seed, // this should be set by other layer.
            title = "very $seed note",
            content = "hello photo note pads $seed",
            date = LocalDate.of(2021, 12, 3),
            color = null,
            isPin = false,
            images = emptyList(),
            createdAt = 999 // this should be set by other layer.
        )
    }
}