package com.alramlawi.pokemonapp.ui.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TagItem(
    tag: String
) {
    Box(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = tag,
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}


@Preview
@Composable
fun PreviewTagItem() {
    TagItem(tag = "Water")
}