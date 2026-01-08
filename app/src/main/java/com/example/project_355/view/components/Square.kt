package com.example.project_355.view.components

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.project_355.generateColor


@Composable
fun Square(
    size: Dp = 10.dp,
    modifier: Modifier = Modifier,
    backGroundColor: Color? = null,
    rounded: String = "full",
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Transparent,
    elevation: Dp = 0.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
){
    val shape = when(rounded) {
        "full" -> CircleShape
        else -> RoundedCornerShape(0.dp)
    }

    val color = backGroundColor ?: remember { generateColor() }

    Box(
        modifier = modifier
            .size(size)
            .then(
                if (elevation > 0.dp) {
                    Modifier.shadow(elevation, shape)
                } else {
                    Modifier
                }
            )
            .then(
                if (borderWidth > 0.dp) {
                    Modifier.border(borderWidth, borderColor, shape)
                } else {
                    Modifier
                }
            )
            .clip(shape)
            .background(color)
            .then(
                if (onClick != null) {
                    Modifier.clickable { onClick() }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center
    ){
        content()
    }
}