package util.tool.provider.use

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import util.log.Logger.log
import util.tool.provider.ProviderRef
import util.tool.provider.provider.normal.Provider
import util.tool.provider.provider.suspend.ProviderSuspend


@Composable
inline fun hookProviderDisposition(provider: Provider<*>) {

    log("[hookProviderDisposition] $provider")

    val ref = ProviderRef()

    DisposableEffect(Unit) {
        ref.getValue(provider)

        onDispose {
            log("[hookProviderDisposition] $provider disposed")

            ref.dispose(provider)
        }
    }

}

@Composable
inline fun hookProviderDisposition(vararg provider: Provider<*>) {

    provider.forEach {
        hookProviderDisposition(it)
    }

}


@Composable
inline fun hookProviderDisposition(provider: ProviderSuspend<*>) {

    log("[hookProviderDisposition] $provider")

    val ref = ProviderRef()

    DisposableEffect(Unit) {
        ref.getValue(provider)

        onDispose {
            log("[hookProviderDisposition] $provider disposed")

            ref.dispose(provider)
        }
    }

}