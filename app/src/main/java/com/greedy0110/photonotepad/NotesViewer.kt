package com.greedy0110.photonotepad

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greedy0110.domain.Note
import com.greedy0110.photonotepad.ui.theme.PhotoNotePadTheme
import com.greedy0110.photonotepad.ui.theme.StickerColors
import java.time.LocalDate

@Composable
fun NotesViewer(modifier: Modifier = Modifier, notes: List<Note>) {
    //TODO: scroll to the top when a new item gets added.
    //TODO: how can I make this list a grid view?
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
    ) {
        items(notes, key = { it.id }) { note ->
            SingleNote(
                modifier = Modifier.padding(4.dp),
                title = note.title,
                content = note.content,
                color = Color(note.color)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun PreviewNotesViewer() {
    fun generateNote(seed: Int): Note {
        return Note(
            id = seed,
            title = "very $seed note",
            content = "hello photo note pads $seed",
            date = LocalDate.of(2021, 12, 3),
            color = StickerColors[seed % StickerColors.size].value,
            isPin = false,
            images = emptyList(),
            createdAt = seed.toLong()
        )
    }

    val all = IntRange(1, 100).map { generateNote(it) }

    PhotoNotePadTheme {
        NotesViewer(notes = all)
    }
}