package composeUtil.navigation

import androidx.compose.runtime.Composable

abstract class ScreenWidget(
    var title: String = "Untitled",
) {

    val key: Int = this.hashCode()

    @Composable
    abstract fun content()

}


fun makeScreen(
    title: String = "Untitled",
    screen: @Composable () -> Unit
) =
    object : ScreenWidget(title) {
        @Composable
        override fun content() = screen()

    }
