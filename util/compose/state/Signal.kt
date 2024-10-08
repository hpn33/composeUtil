package util.compose.state

import androidx.compose.runtime.*
import java.util.UUID
import kotlin.random.Random


@Composable
inline fun useSignal(onReflect: () -> Unit = {}) =
    remember { Signal() }

@Composable
inline fun useSignalSubscribe() =
    remember { SignalSubscribe() }


/**
 * recompose when emit signal
 */
class Signal {
    val state = mutableStateOf(0)

    fun reflect() {
        var newVal = state.value
        while (state.value == newVal) {
            newVal = Random.nextInt(10)
        }

        state.value = newVal
    }

    @Composable
    inline fun hook(crossinline onReflect: () -> Unit = {}) {

        val state = remember { state }
        var ignoreFirst by useState(true)

        LaunchedEffect(state.value) {
            if (ignoreFirst) {
                ignoreFirst = false
                return@LaunchedEffect
            }

            onReflect()
        }

    }

    @Composable
    inline fun hook(key: Any?, crossinline onReflect: () -> Unit = {}) {

        val state = remember(key) { state }
        var ignoreFirst by useState(key, true)

        LaunchedEffect(key, state.value) {
            if (ignoreFirst) {
                ignoreFirst = false
                return@LaunchedEffect
            }

            onReflect()
        }

    }


}

class SignalSubscribe {
    val subscriptions = mutableListOf<Pair<String, () -> Unit>>()

    fun reflect() {
        subscriptions.forEach { it.second.invoke() }
    }

    @Composable
    inline fun hook(noinline onReflect: () -> Unit = {}) {

        val subscribeKey = remember { UUID.randomUUID().toString() }


        DisposableEffect(Unit) {

            subscriptions.add(subscribeKey to onReflect)

            onDispose {
                val index = subscriptions.indexOfFirst { it.first == subscribeKey }

                if (index != -1) {
                    subscriptions.removeAt(index)
                }
            }
        }
    }


    @Composable
    inline fun hook(key: Any?, noinline onReflect: () -> Unit = {}) {

        val subscribeKey = remember(key) { UUID.randomUUID().toString() }


        DisposableEffect(key) {

            subscriptions.add(subscribeKey to onReflect)

            onDispose {
                val index = subscriptions.indexOfFirst { it.first == subscribeKey }

                if (index != -1) {
                    subscriptions.removeAt(index)
                }
            }
        }

    }


}


//fun example() {
//
//
//    val signal = Signal()
//
//
//    val observe = Observe()
//
//
//    observe.observe {
//        println("the signal")
//    }
//
//    observe.observe {
//        println("the second signal")
//    }
//
//}