package util.compose.base

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput


@Composable
inline fun GestureSwitch(
    disabled: Boolean = false,
    content: @Composable () -> Unit
) {

    Box(Modifier
        .composed {
            if (disabled) {
                pointerInput(Unit) {
                    awaitPointerEventScope {
                        // We should wait for all new pointer events
                        while (true) {
                            awaitPointerEvent(pass = PointerEventPass.Initial)
                                .changes
                                .forEach(PointerInputChange::consume)
                        }
                    }
                }
            } else {
                this
            }
        }
    ) {

        content()

    }


}