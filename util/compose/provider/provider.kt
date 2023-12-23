package util.compose.provider

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


// TODO
// [x] use      - recompose with change
// [x] useLocal - get last data as mutableState
// [x] access   - no recomposition
// [x] read     - read current data
//
// [x] dependencies
// [ ] dispose
//
// type:
// [x] value
// [ ] list
// [ ] call input (sample: provider families)
// [ ] suspend load
// [ ] state model
// [ ] ?view model
//
// order:
// - first use
// - depended use
//
// --------------
// old
// [x] read - get/set data without recompose
// [x] watch - recompose by change data
// [X] in connection of providers
// [ ] split - change data/ no change data

abstract class ProviderBase {
    val key: String get() = hashCode().toString()
}

class Provider<T>(
    val builder: InProviderScope.() -> T,
) : ProviderBase()

class FetchProvider<T>(
    val builder: suspend InProviderScope.() -> T,
) : ProviderBase()


class PDelegate<T : Any>(
    val providerService: ProviderService,
    val provider: Provider<T>
) {

//    init {
//        providerService.initAndGet(provider)
//    }

    operator fun getValue(t: T?, property: KProperty<*>) =
        providerService.getState(provider).value as T

    operator fun setValue(t: T?, property: KProperty<*>, value: T) {
        providerService.setState(provider, value)
    }

}

//class ValDelegate<T : Any>(
//    val providerService: ProviderService,
//    val provider: Provider<T>,
//    state: () -> T
//) {
//
//    init {
//        providerService.initAndGet(provider)
//    }
//
//    operator fun getValue(t: T?, property: KProperty<*>) =
//        providerService.getState(provider).value as T
//
//    operator fun setValue(t: T?, property: KProperty<*>, value: T) {
//        providerService.setState(provider, value)
//    }
//
//}


