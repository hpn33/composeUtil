package util.compose.provider

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import util.compose.provider.holder.SuspendState
import util.compose.provider.provider.Provider
import util.compose.provider.provider.SuspendProvider
import util.compose.state.useState
import util.kot.delegate.SetGetDelegate
import util.kot.delegate.setGetVal
import util.log.Logger.log


@Composable
inline fun hookProviderDisposition(provider: Provider<*>) {

    log("[hookProviderDisposition] $provider")

    val ref = ProviderRef()

    DisposableEffect(Unit) {
        ref.getState(provider)

        onDispose {
            log("[hookProviderDisposition] $provider disposed")

            ref.dispose(provider)
        }
    }

}

@Composable
inline fun hookProviderDisposition(vararg provider: Provider<*>) {

    provider.forEach {
        hookProviderDisposition(it)
    }

}


@Composable
inline fun hookProviderDisposition(provider: SuspendProvider<*>) {

    log("[hookProviderDisposition] $provider")

    val ref = ProviderRef()

    DisposableEffect(Unit) {
        ref.getState(provider)

        onDispose {
            log("[hookProviderDisposition] $provider disposed")

            ref.dispose(provider)
        }
    }

}


@Composable
inline fun <T : Any> useProvider(provider: Provider<T>): SetGetDelegate<T> {

    log("[useProvider] $provider")

    val ref = ProviderRef()
    val co = rememberCoroutineScope()

    val state by remember { ref.getState(provider) }

    val v = remember {
        setGetVal(
            set = {
                co.launch {
                    ref.setState(provider, it)
                }
            },
            get = {
                state
            },
        )

    }

    return v
}

@Composable
inline fun <T : Any> useProvider(key: Any, provider: Provider<T>): SetGetDelegate<T> {

//    println("c: [useProvider] ${provider.key}")

    val ref = ProviderRef()
    val co = rememberCoroutineScope()

    val state by remember(key) { ref.getState(provider) }

    val v = remember(key) {
        setGetVal(
            set = {
                co.launch {
                    ref.setState(provider, it)
                }
            },
            get = {
                state
            },
        )

    }

    return v
}


/**
 * is provider, state change by exact selection
 *
 */
@Composable
inline fun <T : Any, R> useProviderSelection(provider: Provider<T>, crossinline selector: (T) -> R): R {

    log("[useProvider] $provider")

    val pro by useProvider(provider)
    var state by useState(selector(pro))

    LaunchedEffect(pro) {

        val newState = selector(pro)

        if (newState != state)
            state = newState

    }

    return state
}


/**
 * short way to use provider value
 * no connection to global state
 */
@Composable
inline fun <T : Any> useProviderLocal(provider: Provider<T>): MutableState<T> {

//    println("c: [useProviderLocal] ${provider.key}")

    val ref = ProviderRef()

    val state = remember { mutableStateOf(ref.getState(provider).value) }

    return state
}

@Composable
inline fun <T : Any> useProviderLocal(key: Any, provider: Provider<T>): MutableState<T> {

//    println("c: [useProviderLocal] ${provider.key}")

    val ref = ProviderRef()

    val state = remember(key) { mutableStateOf(ref.getState(provider).value) }

    return state
}


@Composable
inline fun <T : Any> useProviderAccess(provider: Provider<T>): SetGetDelegate<T> {

//    println("c: [useProviderAccess] ${provider.key}")

    val ref = ProviderRef()
    val co = rememberCoroutineScope()


    val v = remember {
        setGetVal(
            set = {
                co.launch {
                    ref.setState(provider, it)
                }
            },
            get = {
                ref.getState(provider).value
            },
        )

    }

    return v
}

@Composable
inline fun <T : Any> useProviderAccess(key: Any, provider: Provider<T>): SetGetDelegate<T> {

//    println("c: [useProviderAccess] ${provider.key}")

    val ref = ProviderRef()
    val co = rememberCoroutineScope()

    val v = remember(key) {
        setGetVal(
            set = {
                co.launch {
                    ref.setState(provider, it)
                }
            },
            get = {
                ref.getState(provider).value
            },
        )

    }

    return v
}


//@Composable
//inline fun <T : Any> useProviderValue(provider: Provider<T>): T {
//    println("c: [useProviderValue] ${provider.key}")
//
//    val ref = ProviderRef()
//
//    val value = remember { ref.getState(provider).value as T }
//
//    return value
//}
//
//@Composable
//inline fun <T : Any> useProviderValue(key: Any, provider: Provider<T>): T {
//    println("c: [useProviderValue] ${provider.key}")
//
//    val ref = ProviderRef()
//
//    val value = remember(key) { ref.getState(provider).value as T }
//
//    return value
//}


//@Composable
//inline fun <T : Any> readProvider(provider: Provider<T>): SetGetDelegate<T> {
//
//    println("c: [useProvider] ${provider.key}")
//
//    // TODO: as mutableState and able to setValue to service also ??!!
//    val ref = ProviderRef()
//
//    val value = remember { ref.getState(provider).value as T }
//
//    val state = remember {
//        setGetVal<T>(
//            {
//                ref.setState(provider, it)
//            }
//        ) {
//            value
//        }
//    }
//
//    return state
//}


@Composable
inline fun <T : Any?> useProvider(provider: SuspendProvider<T>): SetGetDelegate<SuspendState<T?>> {

    log("[useProvider] $provider")

    val ref = ProviderRef()
    val co = rememberCoroutineScope()

    val state by remember { ref.getState(provider) }

    LaunchedEffect(Unit) {

        co.launch {

            val holder = ref.getHolder(provider)

            holder?.load()

        }


    }


    val v = remember {
        setGetVal(
            get = {
                state
            },
        )

    }

    return v
}

