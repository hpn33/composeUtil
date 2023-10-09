package util.composeUtil.viewModel

import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


//@Composable
//fun <VM : ViewModel> useVM(viewModel: VM): VM {
//
//    val vm = remember { viewModel }
//
//    vm.composeHook()
//
//    DisposableEffect(Unit) {
//
//        vm.init()
//
//        onDispose {
//            vm.dispose()
//        }
//    }
//
//    return vm
//
//}
//
//
//@Composable
//fun <VM : ViewModel> useVM(key: Any, viewModel: VM): VM {
//
//    val vm = remember { viewModel }
//
//    vm.composeHook()
//
//    DisposableEffect(key) {
//
//        vm.init()
//
//        onDispose {
//            vm.dispose()
//        }
//    }
//
//    return vm
//
//}

@Composable
inline fun <S, VM : ViewModel<S>> useVM(key: Any, viewModel: VM): Pair<VM, S> {

    val vm = remember(key) { viewModel }
    val state by vm.hookState()

    vm.composeHook()

    DisposableEffect(key) {

        vm.init()

        onDispose {
            vm.dispose()
        }
    }



    return Pair(vm, state)

}


@Composable
inline fun <S, VM : ViewModel<S>> useVM(viewModel: VM): Pair<VM, S> {

    val vm = remember { viewModel }
    val state by vm.hookState()

    vm.composeHook()

    DisposableEffect(Unit) {

        vm.init()

        onDispose {
            vm.dispose()
        }
    }



    return Pair(vm, state)

}

//abstract class ViewModel {
//
//    protected val vmScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
//
//    @Composable
//    open fun composeHook() {
//    }
//
//    open fun init() {}
//
//    open fun dispose() {}
//
//
//    private val _state = MutableStateFlow(init)
//    val state = _state.asStateFlow()
//    protected val s get() = state.value
//
//    @Composable
//    fun hookState() = state.collectAsState()
//
//    protected fun setState(action: suspend (T) -> T) {
//        vmScope.launch {
//            _state.update { action(it) }
//        }
//    }
//}


abstract class ViewModel<T>(init: T) {


    protected val vmScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    @Composable
    open fun composeHook() {
    }

    open fun init() {}

    open fun dispose() {}


    private val _state = MutableStateFlow(init)
    val state = _state.asStateFlow()
    protected val s get() = state.value

    @Composable
    fun hookState() = state.collectAsState()

    protected fun setState(action: suspend (T) -> T) {
        vmScope.launch {
            _state.update { action(it) }
        }
    }

    protected fun setStateAsync(action: (T) -> T) {
        _state.update { action(it) }
    }

}
