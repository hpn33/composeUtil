package util.tool.provider.provider.suspend

sealed class SuspendState<T> {
    data class Data<T>(val data: T?) : SuspendState<T>()
    data class Loading<T>(val lastData: T?) : SuspendState<T>()
    data class Error<T>(val exception: Exception) : SuspendState<T>()


    inline fun <R> on(error: (Exception) -> R, loading: (T?) -> R, data: (T?) -> R) =
        when (this) {
            is Error -> error(this.exception)
            is Loading -> loading(this.lastData)
            is Data -> data(this.data)
        }


    inline fun <R> onError(error: (Exception) -> R, orElse: () -> R) =
        when (this) {
            is Error -> error(this.exception)
            else -> orElse()
        }

    inline fun <R> onLoading(loading: (T?) -> R, orElse: () -> R) =
        when (this) {
            is Loading -> loading(this.lastData)
            else -> orElse()
        }

    inline fun <R> onData(data: (T?) -> R, orElse: () -> R) =
        when (this) {
            is Data -> data(this.data)
            else -> orElse()
        }


    inline fun <R> onError(error: (Exception) -> R) =
        when (this) {
            is Error -> error(this.exception)
            else -> null
        }

    inline fun <R> onLoading(loading: (T?) -> R) =
        when (this) {
            is Loading -> loading(this.lastData)
            else -> null
        }

    inline fun <R> onData(data: (T?) -> R) =
        when (this) {
            is Data -> data(this.data)
            else -> null
        }


    inline fun onData(): Data<T>? {

        if (this is Data) return this

        return null
    }

    inline fun onLoading(): Loading<T>? {

        if (this is Loading) return this

        return null
    }


    inline fun onError(): Error<T>? {

        if (this is Error) return this

        return null
    }

}
