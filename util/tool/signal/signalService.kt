package util.tool.signal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import java.util.UUID


class SignalService {

//    private val gates = mutableListOf<Any>()

    private val signalsActionMap = mutableMapOf<String, MutableMap<String, Receiver<*>>>()

    fun signal(signalKey: SignalKey<Unit>) = signal(signalKey, Unit)

    fun <T> signal(signalKey: SignalKey<T>, data: T) {

        val receiverMap = getReceiverMap(signalKey)

        receiverMap.values.forEach { it.invoke(data) }

    }


    fun <T> receiverRegister(signalKey: SignalKey<T>, localKey: String, action: (T) -> Unit) {

        val receiverMap = getReceiverMap(signalKey)

        receiverMap[localKey] = action

        println(signalsActionMap)

    }


    fun <T> receiverRemove(signalKey: SignalKey<T>, localKey: String) {

        val receiverList = getReceiverMap(signalKey)

        receiverList.remove(localKey)

    }


    @Composable
    fun <T> receiver(signalKey: SignalKey<T>, action: (T) -> Unit) = receiver(Unit, signalKey, action)

    @Composable
    fun <T> receiver(key: Any?, signalKey: SignalKey<T>, action: (T) -> Unit) {

        val localKey = remember(key) { UUID.randomUUID().toString() }

        DisposableEffect(key) {

            receiverRegister(signalKey, localKey, action)

            onDispose { receiverRemove<T>(signalKey, localKey) }
        }

    }


    private fun <T> getReceiverMap(signalKey: SignalKey<T>) =
        signalsActionMap.getOrPut(signalKey.key, { mutableMapOf() }) as MutableMap<String, Receiver<T>>

}
