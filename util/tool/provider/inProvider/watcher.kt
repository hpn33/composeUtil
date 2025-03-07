package util.tool.provider.inProvider

import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelScope
import util.log.loggerLevelUp
import util.tool.provider.ProviderService
import util.tool.provider.i.ProviderGet
import util.tool.provider.provider.normal.Provider
import util.tool.provider.provider.suspend.ProviderSuspend


open class ProviderWatch<T>(
    val setAction: suspend InProviderWatcherScope.(T, T) -> Unit,
//    val getAction: (T) -> Unit = {}
)

open class ProviderWatchHolder<T>(
    val setAction: suspend (T, T) -> Unit,
//    val getAction: (T) -> Unit = {}
)


class InProviderWatcherScope(
    private val currentProvider: Provider<*>,
    private val providerService: ProviderService
) : ProviderGet {

    suspend fun <T : Any> set(provider: Provider<T>, value: T) {

        log("[InProviderScope] [set] $provider")


        loggerLevelScope {

            providerService.setValue(provider, value)

        }

    }

    override fun <T> get(provider: Provider<T>): T {

        log("[InProviderScope] [get] $provider")


        loggerLevelUp()

        val ph = providerService.getOrRegisterProviderHolder(provider)
        ph.load()

        loggerLevelDown()



        return ph.value as T


    }


    override suspend fun <T : Any> get(provider: ProviderSuspend<T>): T {

        log("[InProviderScope] [get] $provider")


        loggerLevelUp()

        val ph = providerService.getOrRegisterProviderHolder(provider)
        ph.load()

        providerService.depending(currentProvider, dependencyKey = ph.key)

        loggerLevelDown()



        return ph.value as T

    }


}

