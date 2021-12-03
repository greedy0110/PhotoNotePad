package com.greedy0110.domain

class MemoryNoteStore : NoteStore {
    val notes = mutableListOf<Note>()
    var idGenerator = 1

    override suspend fun getAll(): List<Note> {
        return notes
    }

    override suspend fun add(note: Note) {
        notes.add(note.copy(id = getId()))
    }

    private fun getId(): Int {
        return idGenerator++
    }
}