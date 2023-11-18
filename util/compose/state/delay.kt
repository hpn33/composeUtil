package util.compose.state

import androidx.compose.runtime.*
import kotlinx.coroutines.delay


@Composable
inline fun <T> useDelay(value: T, delayDuration: Long): T? {

    var state by remember { mutableStateOf<T?>(null) }

    LaunchedEffect(value) {

        delay(delayDuration)

        state = value

    }

    return state
}

@Composable
inline fun <T> useDelay(key: Any, value: T, delayDuration: Long): T? {

    var state by remember(key) { mutableStateOf<T?>(null) }

    LaunchedEffect(key, value) {

        delay(delayDuration)

        state = value

    }

    return state
}


@Composable
inline fun <T> useDelayAtFirst(value: T, delayDuration: Long): T? {

    var state by remember { mutableStateOf<T?>(null) }
    var atFirst by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {

        if (atFirst) {
            delay(delayDuration)
            atFirst = false
        }

        state = value

    }

    return state
}

@Composable
inline fun <T> useDelayAtFirst(key: Any, value: T, delayDuration: Long): T? {

    var state by remember(key) { mutableStateOf<T?>(null) }
    var atFirst by remember { mutableStateOf(true) }

    LaunchedEffect(key) {

        if (atFirst) {
            delay(delayDuration)
            atFirst = false
        }

        state = value

    }

    return state
}


// with init

@Composable
inline fun <T> useDelayWithInit(value: T, init: T, delayDuration: Long): T {

    var state by remember { mutableStateOf(init) }

    LaunchedEffect(value) {

        delay(delayDuration)

        state = value

    }

    return state
}

@Composable
inline fun <T> useDelayWithInit(key: Any, value: T, init: T, delayDuration: Long): T {

    var state by remember(key) { mutableStateOf(init) }

    LaunchedEffect(key, value) {

        delay(delayDuration)

        state = value

    }

    return state
}


@Composable
inline fun <T> useDelayWithInitAtFirst(value: T, init: T, delayDuration: Long): T {

    var state by remember { mutableStateOf(init) }
    var atFirst by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {

        if (atFirst) {
            delay(delayDuration)
            atFirst = false
        }

        state = value

    }

    return state
}

@Composable
inline fun <T> useDelayWithInitAtFirst(key: Any, value: T, init: T, delayDuration: Long): T {

    var state by remember(key) { mutableStateOf(init) }
    var atFirst by remember { mutableStateOf(true) }

    LaunchedEffect(key) {

        if (atFirst) {
            delay(delayDuration)
            atFirst = false
        }

        state = value

    }

    return state
}