package util.tool.provider.provider.normal

import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelUp
import util.tool.provider.ProviderService
import util.tool.provider.i.ProviderGet
import util.tool.provider.provider.compose.ProviderCompose
import util.tool.provider.provider.suspend.ProviderSuspend

class ProviderScope(
    private val currentProvider: Provider<*>,
    private val providerService: ProviderService
) : ProviderGet {

    override fun <T> get(provider: Provider<T>): T {

        log("[InProviderScope] [get] $provider")


        loggerLevelUp()

        val ph = providerService.getOrRegisterProviderHolder(provider)
        ph.load()

        providerService.depending(currentProvider, dependencyKey = ph.key)

        loggerLevelDown()



        return ph.value as T

    }

    fun <T> get(provider: ProviderCompose<T>): T {

        log("[InProviderScope] [get] $provider")


        loggerLevelUp()

        val ph = providerService.getOrRegisterProviderHolder(provider)
//        ph.load()

        providerService.depending(currentProvider, dependencyKey = ph.key)


        loggerLevelDown()


//        return (ph.state as MutableState<T>).value
        return (ph.valueNullable as T?) ?: provider.default
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


//    fun <T> getCompose(action: @Composable () -> T): T {
//
//        log("[InProviderScope] [get] $action")
//
//
//        loggerLevelUp()
//        val ph = with(provider) { providerService.registerOrGet() }
//        ph.load()
//
//        with(currentProvider) {
//            providerService.depending(
//                dependencyKey = ph.key
//            )
//        }
//
//
//        loggerLevelDown()
//
//
//
//        return (ph.state as MutableState<T>).value
//
//        return get(ProviderCompose<T>(builder = { action() }))
//
//    }
}
