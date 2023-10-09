package util.compose.state

import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
inline fun <T> useDelay(value: T, delayDuration: Long): T? {

    var state by remember { mutableStateOf<T?>(null) }

    LaunchedEffect(Unit) {

        delay(delayDuration)

        state = value

    }

    return state
}

@Composable
inline fun <T> useDelay(key: Any, value: T, delayDuration: Long): T? {

    var state by remember { mutableStateOf<T?>(null) }

    LaunchedEffect(key) {

        delay(delayDuration)

        state = value

    }

    return state
}


@Composable
inline fun <T> useDelayWithInit(value: T, init: T, delayDuration: Long): T {

    var state by remember { mutableStateOf(init) }

    LaunchedEffect(Unit) {

        delay(delayDuration)

        state = value

    }

    return state
}

@Composable
inline fun <T> useDelayWithInit(key: Any, value: T, init: T, delayDuration: Long): T {

    var state by remember { mutableStateOf(init) }

    LaunchedEffect(key) {

        delay(delayDuration)

        state = value

    }

    return state
}


@Composable
inline fun <T> useDelayAtFirst(value: T, init: T, delayDuration: Long): T {

    var state by remember { mutableStateOf(init) }
    var atFirst by remember { mutableStateOf(true) }

    LaunchedEffect(value) {

        if (atFirst) {
            delay(delayDuration)
            atFirst = false
        }

        state = value

    }

    return state
}