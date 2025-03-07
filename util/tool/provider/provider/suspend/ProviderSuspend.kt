package util.tool.provider.provider.suspend

import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelUp
import util.tool.provider.ProviderService
import util.tool.provider.ProviderType
import util.tool.provider.provider.ProviderBase
import util.tool.provider.provider.ProviderBaseReady


class ProviderSuspend<T : Any?>(
    label: String = "",
    val builder: suspend ProviderScopeSuspend.() -> T,
) : ProviderBaseReady<T, SuspendState<T>, ProviderHolderSuspend>(label, type = ProviderType.Suspend) {


    override fun ProviderService.makeHolder(): ProviderHolderSuspend {

//        log("[providerService] [register] $provider")

        val ref = ProviderScopeSuspend(this@ProviderSuspend, this)
        val builder = suspend { ref.builder() }

        val p = ProviderHolderSuspend(
            this@ProviderSuspend.toString(),
            builder,
        )

//        providerHolderDB.add(p)


        return p

    }

    override suspend fun <Type> ProviderService.setState(provider: ProviderBase<Any>, value: Type) {

        log("[providerService] [setState] ${this@ProviderSuspend}")


        val holder = getOrRegisterProviderHolder(provider)
//        val state = getState(provider)

        loggerLevelUp()


        log("[providerService] value ${holder.value}")


        val data = (holder.value as SuspendState<T>?)?.on(error = { null }, loading = { it }) { it }


        holder.value = SuspendState.Loading(data)


        try {

            holder.value = SuspendState.Data(value)

        } catch (e: Exception) {
            holder.value = SuspendState.Error<Any>(e)
        }

        log("[providerService] value ${holder.value}")

        recomputeDependents(this@ProviderSuspend.key)

        loggerLevelDown()

    }


}


