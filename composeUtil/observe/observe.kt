package composeUtil.observe

import androidx.compose.runtime.*


// - [x] signal - observer
// - [ ] multi table act -withDep
// - [ ] split the where use and what use ( location and action key )

// TODO: rename to Observe Catch

data class Observable<T : Any?>(
    val key: String,
    val action: () -> Any?,
) {
    lateinit var dbObservable: DBObserver
    var state: MutableState<T?> = mutableStateOf(null)

    init {
        state.value = action() as T?
    }


    private val actions: MutableList<(T?) -> Unit> = mutableListOf()

    fun changeValue(action: Any?) {

        state.value = action as T?

        actions.forEach { it(state.value) }

    }

    fun onChange(function: (T?) -> Unit) {

        actions.add(function)

    }

    fun onChangeAndDo(function: (T?) -> Unit) {

        onChange(function)

        function(state.value)

    }


    fun renewValue() {
        changeValue(action())
        println("reValued::$key")
    }

    fun free() {
        dbObservable.remove(key)
    }


//    @Composable
//    inline fun hookComposeDisposable(): T? {
//        // TODO: test -maybe not need to update func
//        var data by remember { state }
//
//        DisposableEffect(Unit) {
//
////            println("default")
////            data = value
//
//            onChange {
////                println("onValue Changed")
//                data = it
//            }
//
//            onDispose {
////                println("disposed")
//                free()
//            }
//
//        }
//
//        return data
//
//    }

//    @Composable
//    inline fun hookCompose(): T? {
//        // TODO: test -maybe not need to update func
//        var data by remember { state }
//
//        LaunchedEffect(Unit) {
//
////            println("default")
////            data = value
//
//            onChange {
////                println("onValue Changed")
//                data = it
//            }
//
//        }
//
//        return data
//
//    }

}

class DBObserver {

    private val observers: MutableList<Observable<Any?>> = mutableListOf()

    fun <T> observe(key: String, action: () -> T): Observable<T> {

        var observable = observers.find { it.key == key }

        val wasNotExist = observable == null

        if (wasNotExist) {
            observable = Observable(key, action)
            observable.dbObservable = this
//            observable.tags.addAll(tag)

            observers.add(observable)
        }

        val status = if (wasNotExist) "c" else "g"
        println("observe:$status> $key")

        return observable as Observable<T>
    }

    fun signal(targetKeys: List<String> = listOf()) {

        println("signal>> $targetKeys")
        // act to reacts


        // signal to targets
        var observables = observers.toList()

        if (targetKeys.isNotEmpty()) {
            observables = observables
                .filter { item ->
                    targetKeys.any { targetKey -> item.key.contains(targetKey) }
                }
        }

        observables.forEach { it.renewValue() }
    }

    fun signal(targetKey: String) = signal(listOf(targetKey))


    fun remove(key: String) {

        println("observe:d> $key")

        observers.removeIf { it.key == key }
    }

}
