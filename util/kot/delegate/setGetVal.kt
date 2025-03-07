package util.kot.delegate

import kotlin.reflect.KProperty

inline fun <T> setGetVal(
    noinline set: (T) -> Unit = {},
    noinline get: () -> T
) =
    SetGetDelegate(set, get)

class SetGetDelegate<T>(
    val set: (T) -> Unit,
    val get: () -> T,
) {

    val key = hashCode()

    init {
//        println("[SGDelegate] $key [init]")
    }

    operator fun setValue(t: T?, property: KProperty<*>, value: T) {
//        println("[SGDelegate] $key [set]")
        set(value)
    }


    operator fun getValue(t: T?, property: KProperty<*>): T {
//        println("[SGDelegate] $key [get]")
        return get()
    }
}

//
//inline fun <T> setGetValNullable(
//    noinline set: (T?) -> Unit = {},
//    noinline get: () -> T?
//) =
//    SetGetDelegate(set, get)
//
//class SetGetDelegateNullable<T : Any?>(
//    val set: (T) -> Unit,
//    val get: () -> T,
//) {
//
//    val key = hashCode()
//
//    init {
////        println("[SGDelegate] $key [init]")
//    }
//
//    operator fun setValue(t: T?, property: KProperty<*>, value: T) {
////        println("[SGDelegate] $key [set]")
//        set(value)
//    }
//
//
//    operator fun getValue(t: T?, property: KProperty<*>): T {
////        println("[SGDelegate] $key [get]")
//        return get()
//    }
//}
