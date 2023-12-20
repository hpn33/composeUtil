package util.compose.cacher

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf


// - [x] signal - observer
// - [ ] multi table act -withDep
// - [ ] split the where use and what use ( location and action key )
// - [ ] hook to location and be disposed



data class Observable<T : Any?>(
    val key: String,
    val action: () -> Any?,
    private val actionCatcher: ActionCatcher
) {

    var state: MutableState<T?> = mutableStateOf(null)

    init {
        state.value = action() as T?
    }


    // two type of exe point one without change - one with change
    operator fun invoke(): Observable<T> {
        renewValue()

        return this
    }

    private var localIsExist = true
    val isExist: Boolean
        get() = localIsExist

    private val actions: MutableList<(T?) -> Unit> = mutableListOf()

    fun changeValue(action: Any?) {

        if (!isExist) return

        state.value = action as T?

        actions.forEach { it(state.value) }

    }

    fun onChange(function: (T?) -> Unit) {

        actions.add(function)

    }

    fun onChangeAndDo(function: (T?) -> Unit) {

        onChange(function)

        if (!isExist) return

        function(state.value)

    }


    fun renewValue() {
        changeValue(action())
        println("reValued::$key")
    }

    fun free() {
        actionCatcher.remove(key)
    }

    fun removeFromActionCenter() {
        localIsExist = false
    }


    fun localUse(key: Any): Observable<T> {
        val localKey = this.key + ".local" + key

        return actionCatcher.getOrCreatePoint(localKey) { this.action() as T }
    }

    fun <R> byAction(action: (T?) -> R): ObservableSelector<T, R> {

        return ObservableSelector(this, action)
    }

}


class ActionCatcher {

    private val observers: MutableList<Observable<Any>> = mutableListOf()

    // table@action(parameters)
//    operator fun <T> invoke(key: String, action: () -> T) =
//        actPoint(key, action)


    fun <T> actPoint(key: String, action: () -> T): Observable<T> {

        return getOrCreatePoint(key, action)

    }

    fun <T> getOrCreatePoint(key: String, action: () -> T): Observable<T> {
        var observable = observers.find { it.key == key }

        val wasNotExist = observable == null

        if (wasNotExist) {
            observable = Observable(key, action, this)

            observers.add(observable)
        }

        val status = if (wasNotExist) "c" else "g"
        println("observe:$status> $key")

        return observable as Observable<T>
    }


//    // as signal
//    operator fun invoke(targetKey: String) = signal(targetKey)
//
//    // as signal
//    operator fun invoke(targetKeys: List<String> = listOf()) = signal(targetKeys)
//
//    // as signal
//    operator fun invoke(vararg targetKeys: String) = signal(*targetKeys)

    fun signal(targetKeys: List<String> = listOf(), filterAction: (Observable<Any>) -> Boolean) {


        // signal to targets
        var observables = observers.toList()

        if (targetKeys.isNotEmpty()) {
            observables = observables.filter(filterAction)
        }

        observables.forEach { it.renewValue() }
    }


    fun signalToHaveAnyKey(targetKeys: List<String> = listOf()) {

        println("signal[to have any]>> $targetKeys")

        signal(targetKeys) { item -> targetKeys.any { targetKey -> item.key.contains(targetKey) } }

    }

    fun signalToHaveAnyKey(targetKey: String) = signalToHaveAnyKey(listOf(targetKey))
    fun signalToHaveAnyKey(vararg targetKey: String) = signalToHaveAnyKey(listOf(*targetKey))


    fun signalToHaveAllKey(targetKeys: List<String> = listOf()) {

        println("signal[to have all]>> $targetKeys")

        // act to reacts
        signal(targetKeys) { item -> targetKeys.all { targetKey -> item.key.contains(targetKey) } }

    }

    fun signalToHaveAllKey(targetKey: String) = signalToHaveAllKey(listOf(targetKey))
    fun signalToHaveAllKey(vararg targetKey: String) = signalToHaveAllKey(listOf(*targetKey))


    fun remove(key: String) {

        println("observe:d> $key")

        val observer = observers.find { it.key == key }

        observers.remove(observer)

        observer?.removeFromActionCenter()
    }

}
