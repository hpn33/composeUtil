package util.compose.cacher

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import kotlin.random.Random


//@Composable
//inline fun <T> Observable<T>.hookLocal(): T? {
//// TODO: WHY local ?!?!
//    val local = remember { localUse(Random.nextFloat().toString()) }
//    val state by remember { local.state }
//
//    DisposableEffect(Unit) {
//
//        onDispose {
//            local.free()
//        }
//
//    }
//
//    return state
//
//}


//@Composable
//inline fun <T> Observable<T>.hookDisposable(): T? {
//
//    val state by remember { state }
//
//    DisposableEffect(Unit) {
//
//        onDispose {
//            free()
//        }
//
//    }
//
//    return state
//
//}

@Composable
inline fun <T> Observable<T>.hookSure(): T {

    val data by remember { state }

    return data!!

}


@Composable
inline fun <T> Observable<T>.hook(): T? {

    val data by remember { state }

    return data

}

@Composable
inline fun <T> Observable<T>.hook(key: Any): T? {

    val data by remember(key) { state }

    return data

}


//@Composable
//inline fun <T> useObserver(observable: Observable<T>): T? {
//
//    val observer = remember { observable }.hook()
////    val state = observer.hook()
//
//    return observer
//}
//
//@Composable
//inline fun <T> useObserver(key: Any, observable: Observable<T>): T? {
//
//    val observer = remember(key) { observable }.hook(key)
////    val state = observer.hook(key)
//
//    return observer
//}


@Composable
inline fun <T, R> ObservableSelector<T, R>.hook(): R? {

    val data by remember { this.observable.state }

    return data?.let { this.action(it) }

}
