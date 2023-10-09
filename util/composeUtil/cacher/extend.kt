package util.composeUtil.cacher

import androidx.compose.runtime.*
import kotlin.random.Random


@Composable
inline fun <T> Observable<T>.hookLocal(): T? {
// TODO: WHY local
    val local = remember { localUse(Random.nextFloat().toString()) }
    val state by remember { local.state }

    DisposableEffect(Unit) {

        onDispose {
            local.free()
        }

    }

    return state

}


@Composable
inline fun <T> Observable<T>.hookDisposable(): T? {

    val state by remember { state }

    DisposableEffect(Unit) {

        onDispose {
            free()
        }

    }

    return state

}

@Composable
inline fun <T> Observable<T>.hook(): T? {

    val data by remember { state }

    return data

}
