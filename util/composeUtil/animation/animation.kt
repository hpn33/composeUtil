package util.composeUtil.animation

import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import composeUtil.state.useDelay
import composeUtil.state.useDelayWithInit


@Composable
inline fun AnimatedVisibilityWithDelay(
    visible: Boolean,
    initVisible: Boolean,
    delayDuration: Long,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandIn(),
    exit: ExitTransition = shrinkOut() + fadeOut(),
    label: String = "AnimatedVisibility",
    noinline content: @Composable() AnimatedVisibilityScope.() -> Unit
) {

    val currentVisible = useDelayWithInit(value = visible, init = initVisible, delayDuration)

    AnimatedVisibility(
        currentVisible,
        modifier,
        enter,
        exit,
        label,
        content
    )
}

@Composable
fun AnimatedVisibilityAfter(
    delayDuration: Long = 100,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandIn(),
    exit: ExitTransition = shrinkOut() + fadeOut(),
    label: String = "AnimatedVisibility",
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {

    val currentVisible = useDelayWithInit(value = true, init = false, delayDuration)

    AnimatedVisibility(
        currentVisible,
        modifier,
        enter,
        exit,
        label,
        content
    )
}