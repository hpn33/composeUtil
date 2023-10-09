package util.compose.state

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlin.jvm.JvmInline


// TODO be null by user order
@Composable
inline fun <T> useFetch(
    initValue: T,
    crossinline function: suspend CoroutineScope.(T) -> T
): T {

    var state by useState(initValue)

    LaunchedEffect(Unit) {

        state = function(state)

    }

    return state
}


@Composable
inline fun <T> useFetch(
    key: Any?,
    initValue: T,
    crossinline function: suspend CoroutineScope.(T) -> T
): T {

    var state by useState(key, initValue)

    LaunchedEffect(key) {

        state = function(state)

    }

    return state
}


@Composable
inline fun <T> useFetchList(
    initValue: List<T> = listOf(),
    crossinline function: suspend CoroutineScope.() -> List<T>
): List<T> {
    var state by remember { mutableStateOf(initValue) }

    LaunchedEffect(Unit) {

        val data =
            try {
                function()
            } catch (e: Exception) {
                listOf()
            }

        state = data

    }

    return state
}


@Composable
inline fun <T> useFetchList(
    key: Any?,
    initValue: List<T> = listOf(),
    crossinline function: suspend CoroutineScope.() -> List<T>
): List<T> {
    var state by remember(key) { mutableStateOf(initValue) }

    LaunchedEffect(key) {

        val data =
            try {
                function()
            } catch (e: Exception) {
                listOf()
            }

        state = data
    }

    return state
}


sealed class FetchState<T> {
    data class Error(val e: Exception) : FetchState<Any>()
    data class Loading<T>(val lastData: T?) : FetchState<T?>()
    data class Data<T>(val data: T?) : FetchState<T?>()
}


@JvmInline
value class FetchResult<T>(val fetchValue: FetchState<T>) {

    @Composable
    inline fun on(
        e: @Composable (Exception) -> Unit,
        loading: @Composable (T?) -> Unit,
        data: @Composable (T?) -> Unit
    ) {

        println(fetchValue)
        println()

        when (fetchValue) {
            is FetchState.Error -> {
                e(fetchValue.e)
            }

            is FetchState.Loading<*> -> {
                loading(fetchValue.lastData as T?)
            }

            is FetchState.Data<*> -> {
                data(fetchValue.data as T?)
            }
        }

    }
}


// out of control
@Composable
inline fun <T> useFetchWithHolder(
    initValue: T? = null,
    crossinline function: suspend CoroutineScope.() -> T
): FetchResult<T?> {

    var state by remember { mutableStateOf<FetchResult<*>>(FetchResult(FetchState.Loading(initValue))) }

    LaunchedEffect(Unit) {

        val data = try {

            val v = function()
            FetchState.Data(v)

        } catch (e: Exception) {
            FetchState.Error(e)
        }

        state = FetchResult(data)

    }

    return state as FetchResult<T?>
}

@Composable
inline fun <T> useFetchWithHolder(
    key: Any?,
    initValue: T? = null,
    crossinline function: suspend CoroutineScope.() -> T
): FetchResult<T?> {

    var state by remember(key) { mutableStateOf<FetchResult<*>>(FetchResult(FetchState.Loading(initValue))) }

    LaunchedEffect(key) {

        val data = try {

            val v = function()
            FetchState.Data(v)

        } catch (e: Exception) {
            FetchState.Error(e)
        }

        state = FetchResult(data)

    }

    return state as FetchResult<T?>
}