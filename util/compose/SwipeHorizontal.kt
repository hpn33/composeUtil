package util.compose

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import util.compose.state.useState
import kotlin.math.abs

@Composable
inline fun SwipeHorizontal(
    modifier: Modifier = Modifier,
    crossinline onRight: () -> Unit,
    crossinline onLeft: () -> Unit,
    content: () -> Unit,
) {

    val co = rememberCoroutineScope()

    var swipeLock by useState(false)
    var swipeAmount by useState(0f)


    Box(
        modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->

                    swipeAmount += abs(dragAmount)

                    if (swipeAmount < 50) {
                        co.launch {
                            delay(500)
                            swipeAmount = 0f
                        }
                        return@detectHorizontalDragGestures
                    }

                    if (swipeLock) return@detectHorizontalDragGestures

                    co.launch {

                        if (dragAmount < 0) {

                            onRight()

                        } else if (dragAmount > 0) {
                            onLeft()
                        }



                        swipeLock = true

                        delay(500)
                        swipeLock = false

                        change.consume()
                    }
                }
            }
    ) {
        content()
    }
}