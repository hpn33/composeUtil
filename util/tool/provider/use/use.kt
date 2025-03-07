package util.tool.provider.use

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import util.tool.provider.provider.normal.Provider
import util.kot.delegate.SetGetDelegate
import util.kot.delegate.setGetVal
import util.log.Logger.log
import util.tool.benchmark.rememberDebugStateCounter
import util.tool.provider.ProviderRef
import util.tool.provider.helper.*


@Composable
inline fun <T> useProvider(provider: Provider<T>, scopeSense: Boolean = true): SetGetDelegate<T> {
    return useProvider(Unit, provider, scopeSense)
}


@Composable
inline fun <T> useProvider(
    key: Any,
    provider: Provider<T>,
    scopeSense: Boolean = true
): SetGetDelegate<T> {


    val ref = ProviderRef()
    val co = rememberCoroutineScope()

    val localProviderKey = rememberLocalProviderKey(key)
    val scopeTracker = remember { ref.getValue(providerProviderScopeTracker) }
    val currentScope = remember {
//        if (scopeSense)
        scopeTracker.currentScopeKey ?: "null"
//        else
//            FreeScope
    }

    log(
        "[useProvider] $provider :: ($currentScope) : ${
            localProviderKey.toString().padStart(7, ' ')
        }"
    )

    ProviderHelper.localRegister(key, provider, currentScope, localProviderKey)

//    rememberDebugStateCounter(message = "$localProviderKey " + provider.key)

    var state by remember(key) {
        mutableStateOf(ref.getValue(provider))
    }

    LaunchedEffect(key) {

        ref.observeValue(provider) {
//            println(
//                "observeValue: ${provider.key} : ${localProviderKey.toString().padStart(7, ' ')} ::" +
//                        " (${currentScope}) (${scopeTracker.currentScopeKey})" +
//                        " ${it}"
//            )

            if (!scopeSense || currentScope.isBlank() || scopeTracker.currentScopeKey == currentScope) {

                state = it
            }

        }

        scopeTracker.observeScopeChange { scopeKey, scopeState ->
//            println(
//                "scopeChange: ${provider.key} : ${localProviderKey.toString().padStart(7, ' ')} ::" +
//                        " (${currentScope}) (${scopeTracker.currentScopeKey})" +
//                        " $scopeKey -> $scopeState ${ref.getValue(provider)}"
//            )

            if (!scopeSense || currentScope.isBlank() || scopeKey == currentScope) {
                if (state != ScopeState.Active && scopeSense) return@observeScopeChange


                val value = ref.getValue(provider)
                if (value != state) {
                    state = value
                }
            }

        }

    }


    val v = remember(key) {
        setGetVal<T>(
            set = {
                co.launch {
                    ref.setValue(provider, it)
                }
            },
            get = {
                state
            },
        )

    }

    return v
}


//@Composable
//inline fun <T> useProvider(provider: ProviderSuspend<T>): SuspendState<T> {
//
//    log("[useProvider] $provider")
//
//    val ref = ProviderRef()
//    val co = rememberCoroutineScope()
//
//    val state by remember {
////        ref.getState(provider)
//        ref.getState(provider)
//    }
//
//    LaunchedEffect(Unit) {
//
//        co.launch {
//
//            val holder = ref.getProviderHolder(provider)
//
//            holder?.load()
//
//        }
//
//
//    }
//
//    return state
//}
//
//
//@Composable
//inline fun <T> useProvider(provider: ProviderCompose<T>): T {
//
//    log("[useProvider] $provider")
//
//    val ref = ProviderRef()
//
//    val state by remember {
//        ref.getState(provider)
//    }
//
//    return state ?: provider.default
//}
