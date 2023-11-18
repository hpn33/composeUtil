package util.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import util.compose.state.useDelay

@Composable
inline fun DebugLayout(color: Color = Color.Red, modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    val c = useDelay(true, 10)
    val theColor by animateColorAsState(if (c == true) Color.Transparent else color, tween(2000))

    Box(modifier.background(theColor)) {
        content()
    }
}