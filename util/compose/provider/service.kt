package util.compose.provider

import androidx.compose.runtime.MutableState
import util.compose.provider.holder.*
import util.compose.provider.provider.Provider
import util.compose.provider.provider.SuspendProvider
import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelScope
import util.log.loggerLevelUp


class ProviderService {

    private val providers = mutableListOf<ProviderHolder>()
    private val suspendProviders = mutableListOf<SuspendProviderHolder>()


    fun getHolder(provider: Provider<*>): ProviderHolder? {
        return providers.find { it.key == provider.key }
    }


    fun <T : Any?> register(provider: Provider<T>): ProviderHolder {

//        println("[providerService] [register] $provider")

        val ref = InProviderScope(provider, this)
        val builder = { with(provider) { ref.builder() } as Any }

        val p = ProviderHolder(
            provider.toString(),
            builder,
        )

        providers.add(p)


        return p

    }


    fun <T : Any?> registerOrGet(provider: Provider<T>): ProviderHolder {

        val ph = getHolder(provider)


        if (ph == null) {

            log("[providerService] [register] $provider")

            val p = register(provider)
            p.load()



            return p
        }


//        log("[providerService] [get] $provider")

        return ph
    }


    fun depending(
        provider: Provider<*>,
        dependencyKey: String,
    ) {

//        log("[providerService] [depending] $provider")

        val dependency = getHolder(provider)!!.dependencies

        if (!dependency.contains(dependencyKey)) {
            log("[providerService] [depend] $provider")
            dependency.add(dependencyKey)
        }

//        log(dependency.toString())

    }


    fun dispose(provider: Provider<*>) {

//        log("[providerService] [getState] $provider")

        loggerLevelUp()
        val ph = registerOrGet(provider)
        loggerLevelDown()

        providers.remove(ph)

    }


    fun <T : Any?> getState(provider: Provider<T>): MutableState<T> {

//        log("[providerService] [getState] $provider")

        loggerLevelUp()
        val ph = registerOrGet(provider)
        loggerLevelDown()

        return ph.state as MutableState<T>
    }


    suspend fun <T : Any> setState(provider: Provider<T>, value: T) {

        log("[providerService] [setState] $provider")

        val state = getState(provider)

        loggerLevelUp()

        log("[providerService] value ${state.value}")
        state.value = value
        log("[providerService] value ${state.value}")

        recomputeDependents(provider.key)

        loggerLevelDown()

    }


    private suspend fun recomputeDependents(providerKey: String) {
        log("[providerService] [recomputeDependents] $providerKey")

        loggerLevelScope {

            providers
                .filter { it.dependencies.contains(providerKey) }
//            .also { log("norm: ${it.map { it.key }}") }
                .forEach {

                    log("norm: $providerKey")

                    loggerLevelScope {
                        it.revalue()
                        recomputeDependents(it.key)
                    }
                }

            suspendProviders
                .filter { it.dependencies.contains(providerKey) }
//            .also { log("susp: ${it.map { it.key }}") }
                .forEach {

                    log("susp: $providerKey")

                    loggerLevelScope {
                        it.revalue()
                        recomputeDependents(it.key)
                    }
                }

        }
    }


    /// -------------------------------- Suspend Provider


    fun getHolder(provider: SuspendProvider<*>): SuspendProviderHolder? {
        return suspendProviders.find { it.key == provider.key }
    }


    fun <T : Any?> register(provider: SuspendProvider<T>): SuspendProviderHolder {

//        log("[providerService] [register] $provider")

        val ref = InSuspendProviderScope(provider, this)
        val builder = suspend { with(provider) { ref.builder() } }

        val p = SuspendProviderHolder(
            provider.toString(),
            builder,
        )

        suspendProviders.add(p)


        return p

    }

    fun <T : Any?> registerOrGet(provider: SuspendProvider<T>): SuspendProviderHolder {

        val ph = getHolder(provider)

        if (ph == null) {

            log("[providerService] [register] $provider")

            return register(provider)
        }

//        log("[providerService] [get] $provider")

        return ph
    }

    fun depending(
        provider: SuspendProvider<*>,
        dependencyKey: String,
    ) {

//        log("[providerService] [depending] $provider")

        val dependency = getHolder(provider)!!.dependencies

        if (!dependency.contains(dependencyKey)) {
            log("[providerService] [depend] $provider")
            dependency.add(dependencyKey)
        }


    }

    fun dispose(provider: SuspendProvider<*>) {

//        log("[providerService] [getState] $provider")

        loggerLevelUp()
        val ph = registerOrGet(provider)
        loggerLevelDown()

        suspendProviders.remove(ph)

    }


    fun <T : Any?> getState(provider: SuspendProvider<T>): MutableState<SuspendState<T?>> {

//        log("[providerService] [getState] $provider")

        loggerLevelUp()
        val ph = registerOrGet(provider)
        loggerLevelDown()

        return ph.state as MutableState<SuspendState<T?>>
    }


    suspend fun <T : Any?> setState(provider: SuspendProvider<T>, value: T?) {

        log("[providerService] [setState] $provider")


        val state = getState(provider)

        loggerLevelUp()


        log("[providerService] value ${state.value}")


        val data = state.value.on(error = { null }, loading = { it }) { it }


        state.value = SuspendState.Loading(data)


        try {

            state.value = SuspendState.Data(value)

        } catch (e: Exception) {
            state.value = SuspendState.Error(e)
        }

        log("[providerService] value ${state.value}")

        recomputeDependents(provider.key)

        loggerLevelDown()

    }


}



