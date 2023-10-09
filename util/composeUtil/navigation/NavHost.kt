package util.composeUtil.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier

// TODO: add animation

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navService: NavService = nav,
    rootScreen: ScreenWidget,
) {

    // TODO: dont change when screen is same
    val screenStack = remember { navService.navStack }
    val lastScreen = screenStack.lastOrNull() ?: rootScreen

    Crossfade(
        targetState = lastScreen,
        modifier = modifier
    ) { theScreen ->

        // this make avoid crash when screen remove from stack
        val currentScreen = remember { theScreen }

        currentScreen.content()

    }
}


@Composable
inline fun NavHost(
    modifier: Modifier = Modifier,
    navService: NavService = nav,
    crossinline root: @Composable () -> Unit = { Text("Root") },
) {

    NavHost(modifier, navService, rootScreen = makeScreen { root() })

}


val nav = NavService()

@JvmInline
value class NavService(
    val navStack: SnapshotStateList<ScreenWidget> = mutableStateListOf<ScreenWidget>()
) {

    fun open(screen: ScreenWidget): Boolean {
        if (navStack.isEmpty()) {
            navStack.add(screen)
            return true
        }

        if (navStack.last().title != screen.title) {
            navStack.add(screen)
            return true
        }

        return false
    }

    fun back(): Boolean {
        if (navStack.isNotEmpty()) {
            return navStack.remove(navStack.last())
        }

        return false
    }

    fun backToRoot() = navStack.clear()
    fun backTo(screenKey: Int) {

        val index = navStack.indexOfFirst { it.key == screenKey }

        navStack.removeRange(index + 1, navStack.size)

    }

    fun backTo(screen: ScreenWidget) = backTo(screen.key)

}