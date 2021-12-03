package com.greedy0110.domain

import com.greedy0110.domain.error.NotFoundEntityException

class MemoryNoteStore : NoteStore {
    val notes = mutableListOf<Note>()
    var idGenerator = 1
    var createTime = 0L

    override suspend fun getAll(): List<Note> {
        return notes
    }

    override suspend fun add(note: Note) {
        notes.add(note.copy(id = getId(), createdAt = createTime++))
    }

    override suspend fun update(note: Note) {
        val noteIndex = notes.indexOfFirst { it.id == note.id }
        if (noteIndex == -1) throw NotFoundEntityException()
        notes[noteIndex] = note.copy(id = notes[noteIndex].id)
    }

    private fun getId(): Int {
        return idGenerator++
    }
}