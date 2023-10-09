package util.composeUtil.component.contextMenu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import composeUtil.animation.AnimatedVisibilityAfter
import composeUtil.state.useState
import dateUtil.date.kotlinx.nowLocalDateX
import java.time.LocalDate
import kotlin.random.Random


@Composable
fun DefaultContentBuilder(content: @Composable () -> Unit) {
    DefaultContentMenuPanel {
        AnimatedVisibilityAfter {
            content()
        }
    }
}

// Warning: Fast solutions
val contextMenuOpenKey = mutableStateOf("")

@OptIn(ExperimentalAnimationApi::class)
@Composable
inline fun ContextMenu(
    key: Any? = null,
    crossinline menu: @Composable (() -> Unit) -> Unit,
    crossinline menuBuilder: @Composable (@Composable () -> Unit) -> Unit =
        { DefaultContentBuilder { it() } },
    offset: IntOffset = IntOffset.Zero,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    val key by useState(
        key.toString()
                + Random.nextInt(100).toString()
                + nowLocalDateX().toString()
                + Random.nextInt(100).toString()
    )

    var openedKey by remember { contextMenuOpenKey }

    val toggle = openedKey == key

    Box(modifier) {

        Box(
            Modifier
//                .clip(shape)
                .clickable { openedKey = key }
        ) {

            content()

        }

        AnimatedContent(toggle) {
            if (it) {
                Popup(
                    onDismissRequest = { openedKey = "" },
                    offset = offset,
                ) {

                    menuBuilder { menu { openedKey = "" } }

                }
            }
        }

    }


}

@Composable
inline fun DefaultContentMenuPanel(crossinline content: @Composable () -> Unit) {

    Surface(
        shape = RoundedCornerShape(5.dp),
        elevation = 6.dp
    ) {

        content()

    }

}