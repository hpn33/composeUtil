package util.compose.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
inline fun useSignal() = remember { Signal() }.hook()

/**
 * recompose when emit signal
 */
@JvmInline
value class Signal(
    val state: MutableState<Int> = mutableStateOf(0)
) {


    fun signal() {

        state.value++

        if (state.value > 1000)
            state.value = 0

    }

    @Composable
    inline fun hook(): Signal {
        remember { state }

        return this
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