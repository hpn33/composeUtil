package util.tool.provider

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import util.tool.provider.provider.compose.ProviderHolderCompose


// compose ability: use for store providerService
val LocalProviderScope = compositionLocalOf<ProviderService> { error("not implemented") }

// access to ProviderService
@Composable
inline fun ProviderRef() = LocalProviderScope.current


// base for using ProviderService
@Composable
fun ProviderScope(content: @Composable () -> Unit) {

    CompositionLocalProvider(LocalProviderScope provides ProviderService()) {
        content()

        ProviderComposeTree()

    }

}


@Composable
private fun ProviderComposeTree() {

    val providerService = ProviderRef()

    providerService.providerHolderDB.getComposeType()
        .forEach {
            ProviderComposeAction(it)
        }

}


@Composable
private fun ProviderComposeAction(providerComposeHolder: ProviderHolderCompose) {

    val providerService = ProviderRef()
    val co = rememberCoroutineScope()

    val value = providerComposeHolder.builder()


    providerComposeHolder.value = value
    SideEffect {
        co.launch {
            providerService.recomputeDependents(providerComposeHolder.key)
        }
    }


}


// an area to provide accessibility to ProviderService by ProviderRef
// just this area be recomposed
@Composable
fun ProviderConsumer(content: @Composable (ref: ProviderService) -> Unit) {

    val ref = ProviderRef()

    content(ref)

}

