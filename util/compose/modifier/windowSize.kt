package util.compose.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.onWindowSizeChange(
    onSizeChanged: (IntSize) -> Unit,
) = composed {

    onGloballyPositioned {
        var currentLayoutCoordinates = it.parentLayoutCoordinates

        while (currentLayoutCoordinates != null) {

            if (currentLayoutCoordinates.parentLayoutCoordinates == null) {
                onSizeChanged(currentLayoutCoordinates.size)
            }

            currentLayoutCoordinates = currentLayoutCoordinates.parentLayoutCoordinates

        }

    }

}