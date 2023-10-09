package util.composeUtil.ui.ux

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.unit.dp


@Composable
fun Modifier.highlightLow(color: Color = Color.White) =
    background(color, shape = RoundedCornerShape(15.dp))


@Composable
fun Modifier.highlightMid(color: Color = Color.White) =
    highlightLow(color)
        .border(1.dp, Color.LightGray.copy(.5f), shape = RoundedCornerShape(15.dp))


@Composable
fun Modifier.highlightHigh(color: Color = Color.White, shadow: Float = 3f, shadowColor: Color = DefaultShadowColor) =
    shadow(
        shadow.dp,
        shape = RoundedCornerShape(15.dp),
        ambientColor = shadowColor,
        spotColor = shadowColor
    )
        .highlightMid(color)


@Composable
inline fun PanelHighlightLow(content: @Composable () -> Unit) {

    Box(modifier = Modifier.highlightLow()) {
        content()
    }

}


@Composable
inline fun PanelHighlightMid(content: @Composable () -> Unit) {

    Box(modifier = Modifier.highlightMid()) {
        content()
    }

}

@Composable
inline fun PanelHighlightHigh(content: @Composable () -> Unit) {

    Box(modifier = Modifier.highlightHigh()) {
        content()
    }

}
