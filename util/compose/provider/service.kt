package util.compose.provider

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ProviderHolder(
    val key: String,
    val builder: () -> Any,
    val state: MutableState<Any?> = mutableStateOf(null),
    val dependencies: MutableList<String> = mutableListOf()
) {
    fun revalue() {

        println("[ProviderHolder] $key [revalue]")

        println("--[InProviderScope] $key")

        state.value = builder()

        println("--[\\InProviderScope] $key")

    }

    fun load() {

        if (state.value != null) return

        println("[ProviderHolder] $key [load]")

        revalue()
    }

}


class ProviderService {


//    private val stateMap = mutableMapOf<Int, MutableState<Any>>()
//    private val builderMap = mutableMapOf<Int, (() -> Any)>()
//    private val dependentsMap = mutableMapOf<Int, ArrayList<Int>>() // provider/owner

    private val providers = mutableListOf<ProviderHolder>()

    fun getHolder(provider: Provider<*>): ProviderHolder? =
        getHolder(provider.key)

    fun getHolder(providerKey: String): ProviderHolder? {
        println("[providerService] [getHolder] $providerKey")

        return providers.find { it.key == providerKey }
    }


    fun <T : Any> registerOrGet(provider: Provider<T>): ProviderHolder {

        println("[providerService] [registerOrGet]")

        val key = provider.key
        val ph = getHolder(key)

        if (ph != null) {
            println("[providerService] [get]")
            return ph
        }

        println("[providerService] [register]")

        val ref = InProviderScope(provider, this)
        val builder = { with(provider) { ref.builder() } }

        val p = ProviderHolder(
            key,
            builder,
        )

        providers.add(p)

        return p

    }

    fun depending(
        providerKey: String,
        dependencyKey: String,
    ) {

        println("[providerService] [depending]")

        val dependency = getHolder(providerKey)?.dependencies!!

        if (!dependency.contains(dependencyKey)) {
            println("[providerService] [depend]")
            dependency.add(dependencyKey)
        }


    }


    fun <T : Any> getState(provider: Provider<T>): MutableState<T> {

        println("[providerService] [getState]")

        val ph = initAndGet(provider)

        return ph.state as MutableState<T>
    }


    fun <T : Any> setState(provider: Provider<T>, value: T) {

        println("[providerService] [setState]")


        getState(provider).value = value


        recomputeDependents(provider.key)
    }


    private fun recomputeDependents(providerKey: String) {
        println("[providerService] [recomputeDependents]")

        val dep = providers.filter { it.dependencies.contains(providerKey) }

        dep.forEach { it.revalue() }

    }

    private fun <T : Any> initAndGet(provider: Provider<T>): ProviderHolder {

        println("[providerService] [initAndGet]")

        val ph = registerOrGet(provider)
        ph.load()

        return ph
    }

}


class InProviderScope(
    private val currentProvider: Provider<*>,
    private val providerService: ProviderService
) {

    fun <T : Any> get(provider: Provider<T>): T {

        println("[InProviderScope] ${currentProvider.key} [get] ${provider.key}")


        val ph = providerService.registerOrGet(provider)
        ph.load()

        providerService.depending(
            providerKey = currentProvider.key,
            dependencyKey = ph.key
        )

        return (ph.state as MutableState<T>).value

    }


}