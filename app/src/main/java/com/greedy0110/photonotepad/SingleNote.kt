package com.greedy0110.photonotepad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greedy0110.photonotepad.ui.theme.PhotoNotePadTheme

@Composable
fun SingleNote(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    color: Color
) {
    Card(modifier) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier
                    .background(color.copy(alpha = 0.8f))
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                text = title
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp), color = color
            )
            Text(
                modifier = Modifier
                    .background(color.copy(alpha = 0.5f))
                    .padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                text = content
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun PreviewSingleNote() {
    PhotoNotePadTheme {
        SingleNote(
            modifier = Modifier.padding(8.dp),
            title = "Very First Note.",
            content = "This is a piece of notes.".repeat(10),
            color = Color.Red
        )
    }
}