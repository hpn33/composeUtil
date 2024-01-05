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


data class ProviderHolder(
    val key: String,
    val builder: () -> Any,
    val state: MutableState<Any?> = mutableStateOf(null),
    val dependencies: MutableList<String> = mutableListOf()
) {
    fun revalue() {


        log("[ProviderHolder] $key [revalue]")
//        log("[ProviderHolder] value = ${state.value}")

        loggerLevelScope {
//            log("[InProviderScope] $key")

            state.value = builder()

//            log("[\\InProviderScope] $key")


        }


    }

    fun load() {

        if (state.value != null) return

        log("[ProviderHolder] $key [load]")

        revalue()


    }

}


class InProviderScope(
    private val currentProvider: Provider<*>,
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

    suspend fun <T : Any> get(provider: SuspendProvider<T>): T {

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


}
