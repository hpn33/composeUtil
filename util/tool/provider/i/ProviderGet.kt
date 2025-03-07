package util.tool.provider.i

import util.tool.provider.provider.normal.Provider
import util.tool.provider.provider.suspend.ProviderSuspend

interface ProviderGet {

    fun <T> get(provider: Provider<T>): T

//    fun <T > get(provider: ProviderCompose<T>): T

    suspend fun <T : Any> get(provider: ProviderSuspend<T>): T

}