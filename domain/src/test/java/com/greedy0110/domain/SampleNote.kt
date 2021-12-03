package com.greedy0110.domain

import java.time.LocalDate

object SampleNote {

    private fun generateNote(seed: Int): Note {
        return Note(
            id = seed,
            title = "very $seed note",
            content = "hello photo note pads $seed",
            date = LocalDate.of(2021, 12, 3).plusDays(seed.toLong()),
            color = Note.Color(seed, seed % 0xffffff),
            isPin = false,
            images = emptyList()
        )
    }

    val all = IntRange(1, 100).map { generateNote(it) }
}