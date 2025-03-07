package util.tool.provider.provider.normal

import util.log.Logger.log
import util.log.loggerLevelScope
import util.tool.provider.ProviderType
import util.tool.provider.inProvider.ProviderWatchHolder
import util.tool.provider.provider.ProviderHolderBase


class ProviderHolderProvider(
    key: String,
    val builder: () -> Any,
    state: Any? = null,
    dependencies: MutableList<String> = mutableListOf(),
    watchers: List<ProviderWatchHolder<Any>> = listOf(),
) : ProviderHolderBase(
    key = key,
    providerType = ProviderType.Normal,
    _value = state,
    dependencies = dependencies,
    watchers = watchers
) {

    override fun revalue() {


        log("[ProviderHolder] $key [revalue]")
//        log("[ProviderHolder] value = ${state.value}")

        loggerLevelScope {
//            log("[InProviderScope] $key")

            value = builder()

//            log("[\\InProviderScope] $key")


        }


    }

}


