package util.composeUtil.provider

import androidx.compose.runtime.*
import kotlin.reflect.KProperty

// detail
// name: provider
// description: an injection mechanic
// flow:
// - make a scope on the compose code and down of tree
// - when need to use data can use consumerScope or ScopeRef to access to the data
// - and two type of accessing data exist
//  1- read/change data without recompose
//  2- read/change data with recompose


class Provider<T>(
    val provide: (ref: ProviderService) -> T,
)


class ProviderService {

    // TODO
    // [x] read - get/set data without recompose
    // [x] watch - recompose by change data
    // [X] in connection of providers
    // [ ] split - change data/ no change data


    private val storeMap = mutableMapOf<Int, MutableState<Any>>()
    private val builderMap = mutableMapOf<Int, (() -> Any)>()
    private val dependentsMap = mutableMapOf<Int, ArrayList<Int>>() // provider/owner

    // to track providers in connection dependent
    private var preProvideKey = -1

    private fun <T : Any> ifNotExistInSoreMap(key: Int, provider: Provider<T>) {

        dependentsMap
            .getOrPut(key) { arrayListOf() }
            .takeIf { preProvideKey !in it }
            ?.add(preProvideKey)

        val preKey = preProvideKey
        preProvideKey = key

        val exist = storeMap.containsKey(key)

        if (exist) {
            preProvideKey = preKey

            return
        }


        val value = provider.provide(this)

        storeMap[key] = mutableStateOf(value)
        builderMap[key] = { provider.provide(this) }


        preProvideKey = preKey
    }


    fun <T : Any> provide(provider: Provider<T>): MutableState<T> {

        val key = provider.hashCode()

        ifNotExistInSoreMap(key, provider)

        return storeMap[key] as MutableState<T>

    }

//    fun <T : Any> setValue(provide: Provider<T>, value: T) {
//
//        val key = provide.hashCode()
//
//        ifNotExistInSoreMap(key, value)
//
//        storeMap[key]?.let { it.value = value }
//
//    }

//    fun <T : Any> getValue(provider: Provider<T>): MutableState<T> {
//
//        val key = provider.hashCode()
//
//        ifNotExistInSoreMap(key, provider.provide(this))
//
//        return storeMap[key]!! as MutableState<T>
//    }

    @Composable
    fun <T : Any> read(provider: Provider<T>) =
        remember { ProviderReadDelegate(this, provider) }
//        remember {         mutableStateOf(provide(provider).value) }

    fun <T : Any> watch(provider: Provider<T>) =
        provide(provider)

    fun <T : Any> setState(provider: Provider<T>, value: T) {
        provide(provider).value = value

        recomputeDependents(provider.hashCode())
    }

    private fun recomputeDependents(key: Int) {
        dependentsMap[key]?.let { owners ->
            val statesToRecompute =
                storeMap.filter { owners.contains(it.key) }

            for ((k, state) in statesToRecompute) {
                val builder = builderMap[k] ?: continue
                state.value = builder()
            }
        }

    }

}


class ProviderReadDelegate<T : Any>(
    private val providerService: ProviderService,
    private val provider: Provider<T>,
) {

    private var _value: T =
//        rdScope.getValue(provider).value
        providerService.provide(provider).value

    var value: T
        set(value) {
            this._value = value
//            rdScope.setValue(provider, value)
            providerService.setState(provider, value)
        }
        get() = _value

    operator fun getValue(t: T?, property: KProperty<*>) = value

    operator fun setValue(t: T?, property: KProperty<*>, value: T) {
        this.value = value
    }

    operator fun component1() = value

    operator fun component2(): (T) -> Unit =
        { value -> this.value = value }

}

// compose ability: use for store providerService
val LocalProviderScope = compositionLocalOf<ProviderService> { error("not implemented") }

// access to ProviderService
@Composable
fun ProviderRef() = LocalProviderScope.current


// base for using ProviderService
@Composable
fun ProviderScope(content: @Composable ProviderService.() -> Unit) {

    CompositionLocalProvider(LocalProviderScope provides ProviderService()) {
        ProviderRef().content()
    }

}

// an area to provide accessibility to ProviderService by ProviderRef
// just this area be recomposed
@Composable
fun ProviderConsumer(content: @Composable (ref: ProviderService) -> Unit) {

    val ref = ProviderRef()

    content(ref)

}
