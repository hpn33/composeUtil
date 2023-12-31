package util.compose.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.layout

fun Modifier.vertical() =
    layout { measurable, constraints ->

        val placeable = measurable.measure(constraints)

        layout(placeable.height, placeable.width) {
            placeable.place(
                x = -(placeable.width / 2 - placeable.height / 2),
                y = -(placeable.height / 2 - placeable.width / 2)
            )
        }


    }

fun Modifier.verticalRotateRight() =
    vertical()
        .rotate(90f)

fun Modifier.verticalRotateLeft() =
    vertical()
        .rotate(-90f)