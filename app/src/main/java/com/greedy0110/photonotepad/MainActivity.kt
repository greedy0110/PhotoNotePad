package com.greedy0110.photonotepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.greedy0110.domain.Note
import com.greedy0110.photonotepad.ui.theme.PhotoNotePadTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoNotePadTheme {
                MainScreen(
                    mainViewModel::addRandomNote,
                    mainViewModel.notes
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    onFloatingButtonClick: () -> Unit,
    notes: StateFlow<List<Note>>
) {
    val notesState by notes.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onFloatingButtonClick() }) {
                Icon(Icons.Filled.Add, null)
            }
        }
    ) {
        NotesViewer(notes = notesState)
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    PhotoNotePadTheme {
        MainScreen(
            {},
            MutableStateFlow(emptyList())
        )
    }
}