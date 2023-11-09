package util.compose.component.contextMenu

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import util.compose.animation.AnimatedVisibilityAfter
import util.compose.state.useState


@Composable
fun DefaultContentBuilder(content: @Composable () -> Unit) {
    DefaultContentMenuPanel {
        AnimatedVisibilityAfter {
            content()
        }
    }
}

// Warning: Fast solutions
//val contextMenuOpenKey = mutableStateOf("")

@OptIn(ExperimentalAnimationApi::class)
@Composable
inline fun ContextMenuArea(
//    key: Any? = null,
    crossinline menu: @Composable () -> Unit,
    crossinline menuBuilder: @Composable (@Composable () -> Unit) -> Unit =
        { DefaultContentBuilder { it() } },
    offset: IntOffset = IntOffset.Zero,
    modifier: Modifier = Modifier,
    area: @Composable () -> Unit
) {

//    val key by useState(
//        key.toString()
//                + Random.nextInt(100).toString()
//                + nowLocalDateX().toString()
//                + Random.nextInt(100).toString()
//    )

//    var openedKey by remember { contextMenuOpenKey }

//    val toggle = openedKey == key

    var showMenu by useState(false)

    Box(modifier) {

        Box(
            Modifier
//                .clip(shape)
                .clickable { showMenu = true }
        ) {

            area()

        }

        AnimatedContent(showMenu) {
            if (it) {
                Popup(
                    onDismissRequest = { showMenu = false },
                    offset = offset,
                ) {

                    menuBuilder { menu() }

                }
            }
        }

    }


}

@Composable
inline fun DefaultContentMenuPanel(crossinline content: @Composable () -> Unit) {

    Surface(
        shape = RoundedCornerShape(5.dp),
        elevation = 6.dp,
        modifier = Modifier.sizeIn(minWidth = 100.dp)
    ) {

        content()

    }

}


@Composable
fun ContentMenuItem(label: String, onClick: () -> Unit) {

    Box(
        Modifier
            .height(40.dp)
            .sizeIn(minWidth = 100.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 5.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(label, fontSize = 14.sp)
    }

}