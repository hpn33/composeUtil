package util.compose.cacher


data class ObservableSelector<T, R>(
    val observable: Observable<T>,
    val action: (T?) -> R?
) {

    fun <NR> byAction(action: (R?) -> NR?): ObservableSelector<T, NR> {

        val act = { it: T? -> action(this.action(it)) }

        return ObservableSelector(this.observable, act)
    }

    fun resolve() = action(observable.state.value)

}