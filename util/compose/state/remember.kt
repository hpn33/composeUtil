package util.compose.state

import androidx.compose.runtime.*


@Composable
fun <T> rememberPrevCheck(
    currentValue: T,
    condition: (current: T, prev: T) -> Boolean
): Boolean {

    var prevValue by remember { mutableStateOf(currentValue) }
    val ifIt = remember(currentValue) { condition(currentValue, prevValue) }

    LaunchedEffect(currentValue) {

        prevValue = currentValue

    }

    return ifIt

}