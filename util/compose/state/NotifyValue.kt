package util.compose.state

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier


@Composable
inline fun <T> useNotifyValue(value: T): NotifyValue<T> {
    val state = remember { NotifyValue(value) }

    return state
}

// TODO: Not Tested
// TODO: Not work
class NotifyValue<T : Any?>(var value: T) {

    val signal = Signal()
    val subscribe = mutableListOf<(T) -> Unit>()


    fun notify(value: T) {
        this.value = value

        signal.reflect()
        subscribe.forEach { it.invoke(value) }
    }


    @Composable
    inline fun hook(crossinline onReflect: (T) -> Unit = {}): NotifyValue<T> {
        signal.hook { onReflect(value) }

        return this
    }


    fun observe(action: (T) -> Unit): NotifyValue<T> {

        subscribe.add(action)

        return this
    }


}


@Composable
fun exampleValue() {


    val v = NotifyValue<String?>("")
    v.observe { }
    v.hook { }


}