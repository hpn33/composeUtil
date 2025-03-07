package util.tool.provider

import util.tool.provider.provider.compose.ProviderHolderCompose
import util.tool.provider.provider.ProviderHolderBase
import util.tool.provider.provider.ProviderBase


class ProviderHolderDB {

    //    val holderList = mutableMapOf<ProviderType, MutableList<ProviderHolderBase<*>>>()
    val holderList = mutableListOf<ProviderHolderBase>()


    fun add(p: ProviderHolderBase) {
        holderList.add(p)
    }

    fun getHolder(provider: ProviderBase<*>): ProviderHolderBase? {
//        return holderList[provider.type]!!.find { it.key == provider.key }
        return holderList.find { it.key == provider.key }
    }

    fun getHolderByDependencyKey(key: String): List<ProviderHolderBase> {
        return holderList.filter { it.dependencies.contains(key) }
    }


    fun getComposeType(): List<ProviderHolderCompose> {
        return holderList.filter { it.providerType == ProviderType.Compose } as List<ProviderHolderCompose>
    }

    fun remove(ph: ProviderHolderBase) {
        holderList.remove(ph)
    }

    fun remove(ph: ProviderBase<*>) {

        val holder = getHolder(ph) ?: return

        remove(holder)

    }


}