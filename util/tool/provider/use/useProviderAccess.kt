package util.tool.provider.use

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import util.kot.delegate.SetGetDelegate
import util.kot.delegate.setGetVal
import util.tool.provider.ProviderRef
import util.tool.provider.provider.normal.Provider


@Composable
inline fun <T : Any> useProviderAccess(provider: Provider<T>): SetGetDelegate<T> {

//    println("c: [useProviderAccess] ${provider.key}")

    val ref = ProviderRef()
    val co = rememberCoroutineScope()


    val v = remember {
        setGetVal(
            set = {
                co.launch {
                    ref.setValue(provider, it)
                }
            },
            get = {
                ref.getValue(provider)
            },
        )

    }

    return v
}

@Composable
inline fun <T : Any> useProviderAccess(key: Any, provider: Provider<T>): SetGetDelegate<T> {

//    println("c: [useProviderAccess] ${provider.key}")

    val ref = ProviderRef()
    val co = rememberCoroutineScope()

    val v = remember(key) {
        setGetVal(
            set = {
                co.launch {
                    ref.setValue(provider, it)
                }
            },
            get = {
                ref.getValue(provider)
            },
        )

    }

    return v
}
