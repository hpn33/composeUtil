package util.tool.provider.provider.suspend

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.runBlocking
import util.log.Logger.log
import util.log.loggerLevelScope
import util.tool.provider.ProviderType
import util.tool.provider.provider.ProviderHolderBase


/**
 * - [x] get use
 * - [x] dependencies recompute
 * - [ ] refresh
 * - [x] load state
 *
 *
 *
 */

class ProviderHolderSuspend(
    key: String,
    val builder: suspend () -> Any?,
    state: SuspendState<Any?>? = SuspendState.Data(null),
    dependencies: MutableList<String> = mutableListOf()
//) : ProviderHolderBase<SuspendState<Any?>>(
) : ProviderHolderBase(
    key = key,
    providerType = ProviderType.Suspend,
    _value = state as MutableState<Any?>,
    dependencies = dependencies,
) {
    override fun revalue() {

        log("[SuspendProviderHolder] $key [revalue]")

        loggerLevelScope {

//        println("--[InProviderScope] $key")

            runBlocking {


                val data =
                    when (value as SuspendState<*>) {
                        is SuspendState.Data -> (value as SuspendState.Data<*>).data
                        is SuspendState.Loading -> (value as SuspendState.Loading<*>).lastData
                        else -> null
                    }

                value = SuspendState.Loading(data)


                try {


                    val value = builder()

                    this@ProviderHolderSuspend.value = SuspendState.Data(value)

                } catch (e: Exception) {

                    value = SuspendState.Error<Any>(e)

                }
            }

//        println("--[\\InProviderScope] $key")
        }

    }

}


