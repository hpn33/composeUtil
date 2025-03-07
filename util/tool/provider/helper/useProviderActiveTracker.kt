package util.tool.provider.helper

import androidx.compose.runtime.*
import util.kot.collect.update
import util.kot.collect.updateOrInsert
import util.tool.provider.provider.normal.Provider


enum class ScopeState {
    Active,
    Pause,
    Deactivate,
    Free,
}


data class ScopeData(
    val name: String,
    // if scope not be free
    // react when active
    val reactiveScope: Boolean,
    val state: ScopeState,
) {

    fun isFreeScope() = state == ScopeState.Free
    fun isNotFreeScope() = !isFreeScope()

    fun isScopePause() = state == ScopeState.Pause
    fun isNotPauseScope() = !isScopePause()
}

//const val FreeScope = "[free-scope]"

class ProviderScopeTracker() {

    val subscribe = mutableListOf<(String, ScopeState) -> Unit>()


    //        val stackMap = mutableMapOf<String, ScopeState>()
//    val stack = mutableListOf<String>()
    var list = listOf<ScopeData>()


    //    var lastScopeKey: String? = null
//    var currentScope: String? = null
    var actualPreviousScopeKey: String = "null"
    val actualPreviousScope: ScopeData?
        get() = list.find { it.name == actualPreviousScopeKey }

    var actualCurrentScopeKey: String = "null"
    val actualCurrentScope: ScopeData?
        get() = list.find { it.name == actualCurrentScopeKey }

    var currentScopeKey: String = "null"
    val currentScope: ScopeData?
        get() = list.find { it.name == currentScopeKey }

    var previousScopeKey: String = "null"
    val previousScope: ScopeData?
        get() = list.find { it.name == previousScopeKey }


//    val lastScopeKey: String
//        get() {
//
//            val index = stack.size - 2
//            val current = stack.getOrNull(index).toString()
//
//            return current
//        }
//    val currentScopeKey: String
//        get() {
//
//            val index = stack.size - 1
//            val current = stack.getOrNull(index).toString()
//
//            return current
//        }
//
//
//    val lastScopeKeyNotFreeScope: String
//        get() {
//
//            var index = stack.size - 1
//            var current = stack.getOrNull(index).toString()
//
//
//            while (current == FreeScope) {
//
//                index -= 1
//                current = stack.getOrNull(index).toString()
//
//            }
//
//            index -= 1
//            current = stack.getOrNull(index).toString()
//
//            while (current == FreeScope) {
//
//                index -= 1
//                current = stack.getOrNull(index).toString()
//
//            }
//
//            return current
//        }
//    val currentScopeKeyNotFreeScope: String
//        get() {
//
//            var index = stack.size - 1
//            var current = stack.getOrNull(index).toString()
//
//            while (current == FreeScope) {
//
//                index -= 1
//                current = stack.getOrNull(index).toString()
//
//            }
//
//            return current
//        }

    fun active(key: String, reactiveScope: Boolean) {

        val targetItem =
            list
                .let {
                    it.find { it.name == key }
                        ?: ScopeData(key, reactiveScope, ScopeState.Active)
                }
                .copy(
                    state =
                    if (reactiveScope)
                        ScopeState.Active
                    else
                        ScopeState.Free
                )


        var newList = list.toList()

        // change last one
        actualCurrentScope?.let { currentScope ->

            if (
                !currentScope.reactiveScope
                ||
                !targetItem.reactiveScope
            ) return@let

            newList = newList
                .update(
                    { it.name == currentScope.name }
                ) {
                    it!!.copy(state = ScopeState.Pause)
                }
        }


        // update new one

        newList = newList.updateOrInsert({ it.name == key }) {

            targetItem

        }

        // update new

        if (targetItem.isNotFreeScope()) {
            actualPreviousScopeKey = actualCurrentScopeKey
        }

        previousScopeKey = currentScopeKey
        currentScopeKey = targetItem.name

        if (targetItem.isNotFreeScope()) {
            actualCurrentScopeKey = targetItem.name
        }

        list = newList

        print()
//        notifyScopeChange(key, stackMap[key]!!)
    }

    fun deactivate(key: String) {

//        println("hello $key")
//        println("$list")

        val targetItem = list.find { it.name == key }


        if (targetItem?.state != ScopeState.Free)
            list = list.update({ it.name == key }) { it.copy(state = ScopeState.Deactivate) }


        // last be active
        actualPreviousScope?.let { previousScope ->
            if (previousScope.isScopePause()) {

                list = list.update({ it.name == previousScope.name }) {

                    actualPreviousScopeKey = actualCurrentScopeKey
                    actualCurrentScopeKey = it.name

                    it.copy(state = ScopeState.Active)
                }
            }
        }

//        println("$list")
//        currentScopeKey = key


//        println("deactivate")
//        println("$key $actualCurrentScope")
//        if (key == FreeScope) return
//
////        println(">>> active:$key :: ${currentScopeKey} <- ${lastScopeKey}")
////        println(">>> deactivate:$key :: $currentScopeKey <- $lastScopeKey")
//
//        val indexBottom = stack.indexOfLast { it == key }
//        val indexTop = stack.indexOfLast { it == actualCurrentScope }
//
//        val indexCount = (indexTop - indexBottom).let {
//            if (it == 0) 1 else it
//        }
//        println("$indexBottom - $indexTop = $indexCount")
////        stack.remove
//
//        repeat(indexCount) {
//            println("$indexBottom [$stack]")
//            stack.removeAt(indexBottom)
//        }

//        while (stack.getOrNull(index) != null) {
//            stack.removeAt(index)
//        }


        print()
//        notifyScopeChange(key, stackMap[key]!!)
    }
//    fun active(key: String) {
//
////        if (key == FreeScope)  return
//        println(">>> active:$key :: $currentScopeKey <- $lastScopeKey")
//
////        if (key != FreeScope) {
//        currentScopeKey?.let {
//            if (stackMap[it] == ScopeState.Active) {
//                stackMap[it] = ScopeState.Pause
//                lastScopeKey = it
//            }
//        }
////        }
//
//        stackMap[key] = ScopeState.Active
//
//        currentScopeKey = key
//
//        print()
//        notifyScopeChange(key, stackMap[key]!!)
//    }
//
//    fun deactivate(key: String) {
////        if (key == FreeScope)  return
//
//        println(">>> deactivate:$key :: $currentScopeKey <- $lastScopeKey")
//        stackMap[key] = ScopeState.Deactivate
//
//        lastScopeKey?.let {
//
//            if (stackMap[it] == ScopeState.Pause) {
//                stackMap[it] = ScopeState.Active
//                currentScopeKey = it
//            }
//        }
//
//        print()
//        notifyScopeChange(key, stackMap[key]!!)
//    }


    fun print() {


        list.forEach {
            println(it)
        }

        //        println(">>> $currentScopeKey <- $lastScopeKey")


//        stack.forEach {
//            println(it)
//        }


//        stackMap.keys.forEach {
//            println("$it: ${stackMap[it]}")
//        }
    }

    fun observeScopeChange(action: (String, ScopeState) -> Unit) {
        subscribe.add(action)
    }

    fun notifyScopeChange(key: String, scopeState: ScopeState) {
        subscribe.forEach { it.invoke(key, scopeState) }
    }

}

// todo: warning : if to active exist is work wrong
val ProviderScopeTracker.activeScope: ScopeData?
    get() {


//        val current = currentScopeKey ?: FreeScope
//
//        return if (current == FreeScope)
//            lastScopeKey ?: FreeScope
//        else
//            current

        return list.find { it.state == ScopeState.Active }
    }


val ProviderScopeTracker.activeScopeName: String
    get() {


//        val current = currentScopeKey ?: FreeScope
//
//        return if (current == FreeScope)
//            lastScopeKey ?: FreeScope
//        else
//            current

        return list.find { it.state == ScopeState.Active }?.name ?: "null"
    }

val providerProviderScopeTracker = Provider("ProviderScopeTracker") { ProviderScopeTracker() }

@Composable
fun ProviderHelper.makeProviderScopeFree(label: String) {
//    makeProviderScope(label, false)
}

@Composable
fun ProviderHelper.makeProviderScope(
    label: String,
    reactive: Boolean = true,
//    content: @Composable () -> Unit = {}
) {

//    val key = label.ifBlank { throw Exception("makeProviderScope: label can not be blank") }
//
//    val scopeTracker by useProvider(providerProviderScopeTracker)
//
//    firstTime {
//        scopeTracker.active(key, reactive)
//    }
//
//    DisposableEffect(Unit) {
//
////        println("provider($key): (active)")
//
//        onDispose {
////            println("provider($key): (deactivate)")
//            scopeTracker.deactivate(key)
//        }
//
//    }

    // -------------------- not tested

//    val focus = LocalFocusManager.current
//
//    val f = remember { FocusRequester()}
//
//    LaunchedEffect(Unit) {
//        f.captureFocus()
//    }
//
//    Box(
//        Modifier
//            .onFocusChanged {
//                println("onFocusChanged: $it")
//            }
//            .onFocusEvent {
//                println("onFocusEvent: $it")
//            }
//            .onFocusedBoundsChanged {
//                println("onFocusedBoundsChanged: $it")
//
//            }
//            .focusRequester(f)
//    ) {
//
//        content()
//    }

}


inline fun Any.className(): String {
    return this::class.simpleName.toString()
}