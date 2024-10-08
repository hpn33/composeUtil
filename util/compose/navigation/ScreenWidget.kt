package util.compose.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

abstract class ScreenWidget(
    var title: String = "Untitled",
    var active: MutableState<Boolean> = mutableStateOf(false),
    val enter: EnterTransition = fadeIn(),
    val exit: ExitTransition = fadeOut(),
) {

    val key: Int = this.hashCode()

    @Composable
    abstract fun content()

}


fun makeScreen(
    title: String = "Untitled",
    enter: EnterTransition = fadeIn() + expandIn(),
    exit: ExitTransition = shrinkOut() + fadeOut(),
    screen: @Composable () -> Unit
) =
    object : ScreenWidget(title, enter = enter, exit = exit) {
        @Composable
        override fun content() = screen()

    }



