package util.service.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf


// compose ability: use for store providerService
val LocalProviderScope = compositionLocalOf<ProviderService> { error("not implemented") }

// access to ProviderService
@Composable
inline fun ProviderRef() = LocalProviderScope.current


// base for using ProviderService
@Composable
fun ProviderScope(content: @Composable ProviderService.() -> Unit) {

    CompositionLocalProvider(LocalProviderScope provides ProviderService()) {
        ProviderRef().content()
    }

}

// an area to provide accessibility to ProviderService by ProviderRef
// just this area be recomposed
@Composable
fun ProviderConsumer(content: @Composable (ref: ProviderService) -> Unit) {

    val ref = ProviderRef()

    content(ref)

}

