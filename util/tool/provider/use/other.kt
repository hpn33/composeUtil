package util.tool.provider.use


/**
 * is provider, state change by exact selection
 *
 */
//@Composable
//inline fun <T : Any, R> useProviderSelection(provider: Provider<T>, crossinline selector: (T) -> R): R {
//
//    log("[useProvider] $provider")
//
//    val pro by useProvider(provider)
//    var state by useState(selector(pro))
//
//    LaunchedEffect(pro) {
//
//        val newState = selector(pro)
//
//        if (newState != state)
//            state = newState
//
//    }
//
//    return state
//}
//

/**
 * short way to use provider value
 * no connection to global state
 */
//@Composable
//inline fun <T : Any> useProviderLocal(provider: Provider<T>): MutableState<T> {
//
////    println("c: [useProviderLocal] ${provider.key}")
//
//    val ref = ProviderRef()
//
//    val state = remember {
//        mutableStateOf(
//            with(provider) {
//                ref.getState().value
//            }
//        )
//    }
//
//    return state
//}

//@Composable
//inline fun <T : Any> useProviderLocal(key: Any, provider: Provider<T>): MutableState<T> {
//
////    println("c: [useProviderLocal] ${provider.key}")
//
//    val ref = ProviderRef()
//
//    val state = remember(key) {
//        mutableStateOf(
//            with(provider) {
//                ref.getState().value
//            })
//    }
//
//    return state
//}


//@Composable
//inline fun <T : Any> useProviderValue(provider: Provider<T>): T {
//    println("c: [useProviderValue] ${provider.key}")
//
//    val ref = ProviderRef()
//
//    val value = remember { ref.getState(provider).value as T }
//
//    return value
//}
//
//@Composable
//inline fun <T : Any> useProviderValue(key: Any, provider: Provider<T>): T {
//    println("c: [useProviderValue] ${provider.key}")
//
//    val ref = ProviderRef()
//
//    val value = remember(key) { ref.getState(provider).value as T }
//
//    return value
//}


//@Composable
//inline fun <T : Any> readProvider(provider: Provider<T>): SetGetDelegate<T> {
//
//    println("c: [useProvider] ${provider.key}")
//
//    // TODO: as mutableState and able to setValue to service also ??!!
//    val ref = ProviderRef()
//
//    val value = remember { ref.getState(provider).value as T }
//
//    val state = remember {
//        setGetVal<T>(
//            {
//                ref.setState(provider, it)
//            }
//        ) {
//            value
//        }
//    }
//
//    return state
//}
