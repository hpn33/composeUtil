package util.tool.benchmark

import androidx.compose.runtime.*
import util.compose.state.useState


data class DebugCount(@Stable var c: Int = 0)

var tabLevelGlobal = -1

@Composable
inline fun rememberDebugStateCounter(
    key: Any?,
    tag: String = "recompose",
    message: String = "",
    useTab: Boolean = false
) {

    val tabLevel = remember(key) {
        if (useTab) {
            tabLevelGlobal = 0
        }

        if (!useTab && tabLevelGlobal != -1) {
            tabLevelGlobal += 1
        }

        tabLevelGlobal
    }

    val tab = remember(key) {
        if (tabLevel == -1)
            ""
        else
            List(tabLevel) { " " }.joinToString("") { it }
    }

    val counter = remember(key) { DebugCount() }
    counter.c += 1

    println("$tab$tag: ${counter.c} :$message")

}


@Composable
inline fun rememberDebugStateCounter(
    tag: String = "recompose",
    message: String = "",
    useTab: Boolean = false
) {

    rememberDebugStateCounter(Unit, tag, message, useTab)

}

@Composable
inline fun <T : Any> T.rememberDebugVarCounter(tag: String = "recompose", message: Any? = "") {

    val tabLevel = remember { tabLevelGlobal }

    val tab = remember {
        if (tabLevel == -1)
            ""
        else
            List(tabLevel + 1) { " " }.joinToString("") { it }
    }


    val counter = remember { DebugCount() }
    var isOldState by useState(this, false)

    if (!isOldState) {
        counter.c += 1

        println("$tab- var( $tag ): ${counter.c} :$message")
        isOldState = true
    }
}


