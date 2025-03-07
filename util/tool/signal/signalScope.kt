package util.tool.signal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf


// compose ability: use for store providerService
val LocalSignalScope = compositionLocalOf<SignalService> { error("not implemented") }

// access to ProviderService
@Composable
inline fun SignalRef() = LocalSignalScope.current


// base for using ProviderService
@Composable
fun SignalScope(content: @Composable SignalService.() -> Unit) {

    CompositionLocalProvider(LocalSignalScope provides SignalService()) {
        SignalRef().content()
    }

}

// an area to provide accessibility to ProviderService by ProviderRef
// just this area be recomposed
@Composable
fun SignalConsumer(content: @Composable (ref: SignalService) -> Unit) {

    val ref = SignalRef()

    content(ref)

}

