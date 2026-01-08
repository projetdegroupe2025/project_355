package com.example.project_355

import androidx.compose.ui.graphics.Color

fun generateColor():Color {
    val colors = listOf<Color>(
        Color.Red,
        Color.Blue,
        Color.Green,
        Color.Yellow,
        Color.Cyan,
        Color.Magenta,
        Color.Gray
    )

    return colors.random()
}