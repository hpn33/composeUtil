package util.compose.navigation

import androidx.compose.animation.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import util.compose.state.useDelayWithInit

// TODO: add animation


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavStackHost(
    modifier: Modifier = Modifier,
    navService: NavStackService,
    rootScreen: ScreenWidget,
) {

    // TODO: dont change when screen is same
    val screenStack = remember { navService.navStack }


    for (stackData in screenStack) {

        val activeTarget by remember(stackData.key) { stackData.active }
        val activeCurrent = useDelayWithInit(activeTarget, false, 10)



        LaunchedEffect(activeCurrent, activeTarget) {
            if (!activeCurrent && !activeTarget) {

                delay(300)
                navService.pop(stackData)
            }
        }

        AnimatedVisibility(
            activeCurrent,
            enter = stackData.enter,
            exit = stackData.exit,
        ) {

            stackData.content()

        }
    }
}


@Composable
fun NavStackHost(
    modifier: Modifier = Modifier,
    navService: NavStackService,
    root: @Composable () -> Unit = { Text("Root") },
) {

    NavStackHost(modifier, navService, rootScreen = makeScreen { root() })

}

@JvmInline
value class NavStackService(
    val navStack: SnapshotStateList<ScreenWidget> = mutableStateListOf()
) {

    fun open(screen: ScreenWidget): Boolean {

        navStack.lastOrNull()
            ?.let {
                if (it.title == screen.title) {
                    return false
                }
            }

        navStack.add(screen)
        screen.active.value = true

        return true
    }

    fun back(): Boolean {
        navStack.last().active.value = false

        return true
    }

    fun backToRoot() = navStack.forEach { it.active.value = false }

//    fun backTo(screenKey: Int) {
//
//        val index = navStack.indexOfFirst { it.key == screenKey }
//
//        navStack.removeRange(index + 1, navStack.size)
//
//    }

    //    fun backTo(screen: ScreenWidget) = backTo(screen.key)

    fun popLast(): Boolean {
        if (navStack.isNotEmpty()) {
            return navStack.remove(navStack.last())
        }

        return false
    }

    fun pop(stackData: ScreenWidget): Boolean {
        if (navStack.isNotEmpty()) {
            return navStack.remove(stackData)
        }

        return false
    }

}
