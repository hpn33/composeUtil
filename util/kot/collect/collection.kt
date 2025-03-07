package org.example.util

fun <T> List<T>.unique(filter: (T, T) -> Boolean = { a, b -> a == b }) =
    fold(mutableListOf<T>()) { acc, item ->
        if (!acc.any { filter(it, item) }) {
            acc.add(item)
        }

        acc
    }

//fun <T, R> List<T>.unique(filter: (R, T) -> Boolean, convert: (T) -> R) =
//    fold(mutableListOf<R>()) { acc, item ->
//        if (!acc.any { filter(it, item) }) {
//            acc.add(convert(item))
//        }
//
//        acc
//    }


fun <T> List<T>.walkIn(action: (item: T, actionIn: (T) -> Unit) -> Unit) {

    fun actionIn(item: T) = action(item, ::actionIn)


    this.forEach {
        action(it, ::actionIn)
    }


}


fun <T> List<T>.walkInWithParent(action: (parent: T?, item: T, actionIn: (parent: T?, item: T) -> Unit) -> Unit) {

    fun actionIn(parent: T?, item: T) = action(parent, item, ::actionIn)


    this.forEach {
        action(null, it, ::actionIn)
    }

}
