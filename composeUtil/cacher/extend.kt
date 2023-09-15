package composeUtil.cacher

import androidx.compose.runtime.*


//@Composable
//inline fun <T> Observable<T>.hookComposeDisposable(): T? {
//    // TODO: test -maybe not need to update func
//    var data by remember { state }
//
//    DisposableEffect(Unit) {
//
////            println("default")
////            data = value
//
//        onChange {
////                println("onValue Changed")
//            data = it
//        }
//
//        onDispose {
////                println("disposed")
//            free()
//        }
//
//    }
//
//    return data
//
//}

//@Composable
//inline fun <T> Observable<T>.hookCompose(): T? {
//    // TODO: test -maybe not need to update func
//    var data by remember { state }
//
//    LaunchedEffect(Unit) {
//
////            println("default")
////            data = value
//
//        onChangeAndDo {
////                println("onValue Changed")
//            data = it
//        }
//
//    }
//
//    return data
//
//}
