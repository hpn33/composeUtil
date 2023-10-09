package util.compose.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState

// Not Tested
class NotifyValue<T : Any>(var value: T) {

    val signal = Signal()
    val subscribe = mutableListOf<(T) -> Unit>()

    val notifyState: MutableState<Int>
        get() = signal.state
    val notifier: Int
        get() = notifyState.value


    fun notify(value: T?) {
        value?.let { this.value = it }

        signal.signal()
        subscribe.forEach { it.invoke(this.value) }
    }

    @Composable
    inline fun onNotify(noinline action: (T) -> Unit) {

        LaunchedEffect(Unit) {
            subscribe.add(action)
        }

    }


    @Composable
    inline fun hook(): NotifyValue<T> {
        signal.hook()

        return this
    }


    @Composable
    inline fun observe(crossinline action: (T) -> Unit): NotifyValue<T> {

        LaunchedEffect(notifier) {
            action(value)
        }

        return this
    }


}
