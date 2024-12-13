package util.compose.state

import androidx.compose.runtime.*
import kotlin.reflect.KProperty

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


data class NonState<T>(var value: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }
}

@Composable
inline fun <T> useNonState(value: T) =
    remember { NonState(value) }

@Composable
inline fun <T> useNonState(key: Any? = Unit, value: T) =
    remember(key) { NonState(value) }
