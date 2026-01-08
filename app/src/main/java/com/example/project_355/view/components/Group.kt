package com.example.project_355.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

@Composable

fun <T> Group(
    data: List<T>,
    modifier: Modifier = Modifier,
    onItemClick: ((T, Int) -> Unit)? = null,
    firstActiveIndex: Int = 0,
    orientation: String = "horizontal",
    renderer: @Composable (T, isActive: Boolean, onClick: () -> Unit) -> Unit
) {

    var activeIndex by remember { mutableStateOf(firstActiveIndex) }

    if(orientation == "horizontal"){
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            data.forEachIndexed { index, item ->
                renderer(item, activeIndex == index) {
                    activeIndex = index
                    onItemClick?.invoke(item, index)
                }

            }

        }
    }else{
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            data.forEachIndexed { index, item ->
                renderer(item, activeIndex == index) {
                    activeIndex = index
                    onItemClick?.invoke(item, index)
                }

            }

        }
    }



}
