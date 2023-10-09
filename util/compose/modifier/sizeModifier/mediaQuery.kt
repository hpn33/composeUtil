package util.compose.modifier.sizeModifier

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.IntSize
import util.compose.modifier.onWindowSizeChange
import util.compose.state.useState

//import hpn.woo.shoo.common.util.compose.useState

/**
 * use
 * -[onWindowSizeChange]
 * -[useState]
 */

@Composable
inline fun MediaQueryBox(
    modifier: Modifier = Modifier,
    content: @Composable (windowSize: IntSize) -> Unit,
) {

    var windowSize by useState(IntSize.Zero)

    Box(
        modifier = modifier.onWindowSizeChange { windowSize = it }
    ) {

        content(windowSize)

    }

}


inline fun Modifier.mediaQuery(
    crossinline modifier: Modifier.(IntSize) -> Modifier,
) = composed {

    var windowSize by useState(IntSize.Zero)

    onWindowSizeChange { windowSize = it }

    modifier(windowSize)
}

