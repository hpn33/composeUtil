package util.tool.provider.provider.suspend

import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelUp
import util.tool.provider.ProviderService
import util.tool.provider.provider.normal.Provider

class ProviderScopeSuspend(
    private val currentProvider: ProviderSuspend<*>,
    private val providerService: ProviderService
) {

    fun <T> get(provider: Provider<T>): T {

        log("[InProviderScope] [get] $provider")



        loggerLevelUp()

        val ph = providerService.getOrRegisterProviderHolder(provider)
        ph.load()

        providerService.depending(currentProvider, dependencyKey = ph.key)


        loggerLevelDown()



        return ph.value as T

    }

    suspend fun <T> get(provider: ProviderSuspend<T>): SuspendState<T> {

        log("[InProviderScope] [get] $provider")


        loggerLevelUp()

        val ph = providerService.getOrRegisterProviderHolder(provider)
        ph.load()


        providerService.depending(currentProvider, dependencyKey = ph.key)

        loggerLevelDown()


//        return (ph.state as MutableState<T>).value
        return ph.value as SuspendState<T>

    }


}