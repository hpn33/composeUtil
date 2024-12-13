package org.example.util

import org.example.core.ast.LElement
import org.example.core.ast.LElementScope


//fun Any.println() = println(this)
fun Any?.println() = println(this)
fun Collection<*>?.printList() = this?.forEach { println(it) }
fun List<*>?.printList() = this?.forEach { println(it) }

fun <T> T.alsoPrintln() = also { println(it) }
fun <T> List<T>.alsoPrintList() = onEach { println(it) }
fun <T, R> Map<T, R>.alsoPrintList() = onEach { println(it) }


fun List<LElement>.printList() = forEach { it.println() }
fun LElement.println() {
//    println("${this::class.simpleName}(${this.key}) ${this.children.size} children")
    val childInfo =
        if (this is LElementScope) "(${this.children.size} children)" else ""

//    println("${this.key} <<${this::class.simpleName}>> $childInfo")
    println("${this.key} $childInfo")

    this.children.printList()

}
