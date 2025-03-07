package util.kot.delegate

import kotlin.reflect.KProperty


inline fun <T : Any?> getVal(
//    noinline set: (T) -> Unit = {},
    noinline get: () -> T
) =
    GetDelegate(get)

class GetDelegate<T : Any?>(
//    val set: (T) -> Unit,
    val get: () -> T,
) {

    val key = hashCode()

    init {
//        println("[SGDelegate] $key [init]")
    }

//    operator fun setValue(t: T?, property: KProperty<*>, value: T) {
////        println("[SGDelegate] $key [set]")
//        set(value)
//    }


    operator fun getValue(t: T?, property: KProperty<*>): T {
//        println("[SGDelegate] $key [get]")
        return get()
    }
}