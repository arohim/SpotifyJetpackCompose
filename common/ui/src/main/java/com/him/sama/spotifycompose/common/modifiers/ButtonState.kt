package com.him.sama.spotifycompose.common.modifiers

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class ButtonState { Pressed, Idle }

@Stable
fun Modifier.bounceClick(onClick: () -> Unit): Modifier = composed {
    var buttonState by remember { mutableStateOf(ButtonState.Idle) }
    val scale by animateFloatAsState(
        if (buttonState == ButtonState.Pressed) 0.90f else 1f,
        label = ""
    )
    val coroutineScope = rememberCoroutineScope()
    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                coroutineScope.launch {
                    buttonState = ButtonState.Pressed
                    delay(100)
                    buttonState = ButtonState.Idle
                }
            }
        }
}