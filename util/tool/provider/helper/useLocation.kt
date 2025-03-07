package util.tool.provider.helper

import androidx.compose.runtime.*
import util.tool.provider.ProviderRef
import util.tool.provider.provider.normal.Provider


@Composable
inline fun ProviderHelper.localRegister(key: Any, provider: Provider<*>, scope: String, localProviderKey: Int) {

    val ref = ProviderRef()

//
    DisposableEffect(key) {
//        val scope = scopeTracker.currentScopeKey ?: ""

        ref.localRegister(provider, scope, localProviderKey)

        onDispose {
            ref.localRegisterRemove(provider, scope, localProviderKey)
        }
    }


}
