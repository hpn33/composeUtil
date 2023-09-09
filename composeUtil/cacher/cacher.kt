package composeUtil.cacher

import androidx.compose.runtime.*


// - [x] signal - observer
// - [ ] multi table act -withDep
// - [ ] split the where use and what use ( location and action key )

data class Observable<T : Any?>(
    val key: String,
    val action: () -> Any?,
) {
    lateinit var dbObservable: ActionCatcher
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

}

class ActionCatcher {

    private val observers: MutableList<Observable<Any>> = mutableListOf()

    // table@action(parameters)
    fun <T> actPoint(key: String, action: () -> T): Observable<T> {

        return getOrCreatePoint(key, action)

    }

    private fun <T> getOrCreatePoint(key: String, action: () -> T): Observable<T> {
        var observable = observers.find { it.key == key }

        val wasNotExist = observable == null

        if (wasNotExist) {
            observable = Observable(key, action)
            observable.dbObservable = this

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
