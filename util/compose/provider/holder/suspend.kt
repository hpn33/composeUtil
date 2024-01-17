package util.compose.provider.holder

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import util.compose.provider.ProviderService
import util.compose.provider.provider.Provider
import util.compose.provider.provider.SuspendProvider
import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelScope
import util.log.loggerLevelUp


/**
 * - [x] get use
 * - [x] dependencies recompute
 * - [ ] refresh
 * - [x] load state
 *
 *
 *
 */


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

data class SuspendProviderHolder(
    val key: String,
    val builder: suspend () -> Any?,
    val state: MutableState<SuspendState<Any?>> = mutableStateOf(SuspendState.Data(null)),
    val dependencies: MutableList<String> = mutableListOf()
) {
    suspend fun revalue() {

        log("[SuspendProviderHolder] $key [revalue]")

        loggerLevelScope {

//        println("--[InProviderScope] $key")

            val data =
                when (state.value) {
                    is SuspendState.Data -> (state.value as SuspendState.Data).data
                    is SuspendState.Loading -> (state.value as SuspendState.Loading).lastData
                    else -> null
                }

            state.value = SuspendState.Loading(data)


            try {


                val value = builder()

                state.value = SuspendState.Data(value)

            } catch (e: Exception) {

                state.value = SuspendState.Error(e)

            }

//        println("--[\\InProviderScope] $key")
        }

    }

    suspend fun load() {

        if (state.value is SuspendState.Data)
            if ((state.value as SuspendState.Data).data != null) return

        log("[SuspendProviderHolder] $key [load]")

        revalue()
    }

}


class InSuspendProviderScope(
    private val currentProvider: SuspendProvider<*>,
    private val providerService: ProviderService
) {

    fun <T : Any> get(provider: Provider<T>): T {

        log("[InProviderScope] [get] $provider")



        loggerLevelUp()

        val ph = providerService.registerOrGet(provider)
        ph.load()

        providerService.depending(
            provider = currentProvider,
            dependencyKey = ph.key
        )

        loggerLevelDown()



        return (ph.state as MutableState<T>).value

    }

    suspend fun <T : Any?> get(provider: SuspendProvider<T>): SuspendState<T> {

        log("[InProviderScope] [get] $provider")


        loggerLevelUp()

        val ph = providerService.registerOrGet(provider)
        ph.load()

        providerService.depending(
            provider = currentProvider,
            dependencyKey = ph.key
        )

        loggerLevelDown()


//        return (ph.state as MutableState<T>).value
        return ph.state.value as SuspendState<T>

    }


}