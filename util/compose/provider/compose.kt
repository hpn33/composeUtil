package util.compose.provider

import androidx.compose.runtime.*
import util.kot.delegate.SetGetDelegate
import util.kot.delegate.setGetVal


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


@Composable
inline fun <T : Any> useProvider(provider: Provider<T>): SetGetDelegate<T> {

    println("c: [useProvider] ${provider.key}")

    val ref = ProviderRef()

    val state by remember { ref.getState(provider) }

    val v = remember {
        setGetVal(
            set = {
                ref.setState(provider, it)
            },
            get = {
                state
            },
        )

    }

    return v
}

@Composable
inline fun <T : Any> useProviderLocal(provider: Provider<T>): MutableState<T> {

    println("c: [useProviderLocal] ${provider.key}")

    val ref = ProviderRef()

    val state = remember { mutableStateOf(ref.getState(provider).value) }

    return state
}


@Composable
inline fun <T : Any> accessProvider(provider: Provider<T>): SetGetDelegate<T> {

    println("c: [accessProvider] ${provider.key}")

    val ref = ProviderRef()

    val v = remember {
        setGetVal(
            set = {
                ref.setState(provider, it)
            },
            get = {
                ref.getState(provider).value
            },
        )

    }

    return v
}


@Composable
inline fun <T : Any> readProvider(provider: Provider<T>): T {

    val ref = ProviderRef()

    val value = remember { ref.getState(provider).value as T }

    return value
}
