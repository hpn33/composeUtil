package util.compose.tree

import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember


//typealias MoveInvoke<T> = @Composable (item: T) -> Unit


//@Composable
//fun <T> List<T>.movable(
//    transform: @Composable (item: T) -> Unit
//): @Composable (item: T) -> Unit {
//    val composedItems = remember(this) {
//        mutableMapOf<T, @Composable () -> Unit>()
//    }
//
//    return { item: T ->
//        composedItems.getOrPut(item) {
//            movableContentOf {
//                transform(item)
//            }
//        }
//    }
//
//}

@Composable
fun <T> movableHost(
    key: Any? = Unit,
    transform: @Composable (item: T) -> Unit
): @Composable (item: T) -> Unit {
    val composedItems = remember(key) {
        mutableMapOf<T, @Composable () -> Unit>()
    }

    return { item: T ->
        composedItems.getOrPut(item) {
            movableContentOf {
                transform(item)
            }
        }
    }

}