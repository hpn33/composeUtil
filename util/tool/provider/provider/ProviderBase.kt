package util.tool.provider.provider

import util.tool.provider.ProviderType


abstract class ProviderBase<Type>(
    val label: String,
    val providerType: ProviderType,
) {
    private val randomKey: String get() = hashCode().toString()
    val key: String get() = "$label($randomKey)"

    override fun toString() = key
}
