package util.composeUtil.state

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope

@Stable
@Composable
inline fun <T> State<T>.observe(crossinline action: (T) -> Unit) {

    LaunchedEffect(this.value) {

        action(value)

    }

}


@Composable
inline fun <T> useState(value: T) =
    remember { mutableStateOf(value) }

@Composable
inline fun <T> useState(key: Any?, value: T) =
    remember(key) { mutableStateOf(value) }
