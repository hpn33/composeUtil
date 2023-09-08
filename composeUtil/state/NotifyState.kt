package composeUtil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import composeUtil.state.Signal

inline fun <T> notifyStateOf(value: T) = NotifyState(value)

@Composable
inline fun <T> useNotifyState(value: T) = notifyStateOf(value).hook()

class NotifyState<T>(var value: T) {

    val signal = Signal()

    val notifyState: MutableState<Int>
        get() = signal.state
    val notifier: Int
        get() = notifyState.value


    fun notify(value: T?) {
        value?.let { this.value = it }

        signal.signal()
    }

    @Composable
    inline fun hook(): NotifyState<T> {
        signal.hook()

        return this
    }


    @Composable
    inline fun observe(crossinline action: (T) -> Unit): NotifyState<T> {

        LaunchedEffect(notifier) {
            action(value)
        }

        return this
    }


}


@Composable
fun example() {

    val state = NotifyState("")




    state.notify("notify")

    state.value = "new"

    state.value

}