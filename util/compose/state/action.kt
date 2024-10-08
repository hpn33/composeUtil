package util.compose.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope


@Composable
inline fun firstTimeEffect(crossinline action: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(Unit) {
        action()
    }
}

@Composable
inline fun firstTime(crossinline action: () -> Unit) {
    var isInit by useState(false)

    if (!isInit) {
        action()
        isInit = true
    }
}

@Composable
inline fun firstTime(
    key: Any = Unit,
    crossinline action: () -> Unit
) {
    var isInit by useState(key, false)

    if (!isInit) {
        action()
        isInit = true
    }
}