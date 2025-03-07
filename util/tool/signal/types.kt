package util.tool.signal

class SignalKey<T> {
    val key: String
        get() = hashCode().toString()
//    = UUID.randomUUID().toString()
}

//data class Realm<T>(
//    val action: (T) -> Unit
//)

typealias Receiver<T> = (T) -> Unit