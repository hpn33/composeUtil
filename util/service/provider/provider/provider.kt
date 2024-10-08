package util.service.provider.provider

import util.service.provider.holder.InProviderScope
import util.service.provider.holder.InProviderWatcherScope
import util.service.provider.holder.InSuspendProviderScope

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
// [x] access   - no recomposition
// [x] useLocal - get last data as mutableState
// [x] read     - read current data (removed)
//
// [x] dependencies
// [ ] dispose
//
// type:
// [x] value
// [ ] list
// [ ] call input (sample: provider families)
// [x] suspend load
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

abstract class ProviderBase(
    val label: String
) {
    private val randomKey: String get() = hashCode().toString()
    val key: String get() = "$label($randomKey)"

    override fun toString() = key
}

class Provider<T>(
    label: String = "",
    val watchers: List<ProviderWatch<T>> = emptyList(),
    val builder: InProviderScope.() -> T,
) : ProviderBase(label)

class SuspendProvider<T : Any?>(
    label: String = "",
    val builder: suspend InSuspendProviderScope.() -> T,
) : ProviderBase(label)


// ----------------


open class ProviderWatch<T>(
    val setAction: suspend InProviderWatcherScope.(T, T) -> Unit,
//    val getAction: (T) -> Unit = {}
)

open class ProviderWatchHolder<T>(
    val setAction: suspend (T, T) -> Unit,
//    val getAction: (T) -> Unit = {}
)

//fun <T> providerWatch(
//    set: (T) -> Unit = {},
////    get: (T) -> Unit = {}
//) = ProviderWatch(set)