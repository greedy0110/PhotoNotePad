package com.greedy0110.photonotepad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedy0110.domain.ColorStore
import com.greedy0110.domain.Note
import com.greedy0110.domain.NoteStore
import com.greedy0110.domain.usecase.CreateSingleNoteUseCase
import com.greedy0110.domain.usecase.GetAllNotesUseCase
import com.greedy0110.domain.usecase.GetRandomColorUseCase
import com.greedy0110.local.MemoryColorStore
import com.greedy0110.local.MemoryNoteStore
import com.greedy0110.photonotepad.ui.theme.StickerColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel : ViewModel() {

    private val noteStore: NoteStore = MemoryNoteStore()
    private val colorStore: ColorStore = MemoryColorStore()

    init {
        //TODO: this function call should be replaced to a proper place to call.
        //  this action should be called only once when the application started.
        viewModelScope.launch {
            colorStore.saveOrder(StickerColors.map { it.value })
        }
    }

    private val _notes: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    private var cnt = 0
    fun addRandomNote() {
        viewModelScope.launch {
            val randomColorCode = GetRandomColorUseCase(colorStore).execute()
            val createUseCase = CreateSingleNoteUseCase(noteStore)
            createUseCase.execute(generateNote(cnt++).copy(color = randomColorCode))

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
            color = 0u,
            isPin = false,
            images = emptyList(),
            createdAt = 999 // this should be set by other layer.
        )
    }
}