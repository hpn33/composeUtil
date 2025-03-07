package util.tool.provider.provider.normal

import util.log.Logger.log
import util.tool.provider.ProviderService
import util.tool.provider.ProviderType
import util.tool.provider.inProvider.InProviderWatcherScope
import util.tool.provider.inProvider.ProviderWatch
import util.tool.provider.inProvider.ProviderWatchHolder
import util.tool.provider.provider.ProviderBaseReady

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


class Provider<T>(
    label: String = "",
    val watchers: List<ProviderWatch<T>> = emptyList(),
    val builder: ProviderScope.() -> T,
) : ProviderBaseReady<T, T, ProviderHolderProvider>(label, ProviderType.Normal) {


    override fun ProviderService.makeHolder(): ProviderHolderProvider {

//        println("[providerService] [register] $provider")

        val ref = ProviderScope(this@Provider, this)
        val builder = { ref.builder() as Any }

        val refWatcher = InProviderWatcherScope(this@Provider, this)
        val watcherHolder =
            this@Provider.watchers.map { watcher ->

                val action: suspend (T, T) -> Unit = { oldValue, value ->
                    with(watcher) { refWatcher.setAction(oldValue, value) } as Any
                }

                ProviderWatchHolder(action)

            } as List<ProviderWatchHolder<Any>>


        val p = ProviderHolderProvider(
            this@Provider.toString(),
            builder,
            watchers = watcherHolder
        )

//        providerHolderDB.add(p)


        return p

    }

}




