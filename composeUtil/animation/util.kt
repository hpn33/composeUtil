package composeUtil.animation

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.*


@Composable
inline fun animateFloatAsStateOverState(
    state: Any,
    initValue: Float = 0f,
    animationSpec: AnimationSpec<Float> = spring(),
    crossinline act: suspend ((Float) -> Unit) -> Unit,
): Float {

    var offY by remember { mutableStateOf(initValue) }

    val animation by animateFloatAsState(offY, animationSpec)

    LaunchedEffect(state) {
        act { offY = it }
    }

    return animation
}

@Composable
inline fun animateFloatAsStateOverState(
    key: Any,
    state: Any,
    initValue: Float = 0f,
    animationSpec: AnimationSpec<Float> = spring(),
    crossinline act: suspend ((Float) -> Unit) -> Unit,
): Float {

    var offY by remember(key) { mutableStateOf(initValue) }

    val animation by animateFloatAsState(offY, animationSpec)

    LaunchedEffect(key, state) {
        act { offY = it }
    }

    return animation
}