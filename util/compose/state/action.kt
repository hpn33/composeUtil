package util.compose.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope


@Composable
inline fun firstTime(crossinline action: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(Unit) {
        action()
    }
}