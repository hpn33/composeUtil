package composeUtil.dialog.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composeUtil.dialog.component.DialogController
import java.util.*


enum class DialogShowType {
    None, Window, Overlay
}

abstract class DialogWidget(
    val name: String = UUID.randomUUID().toString(),

    // controller
    val title: String = "Untitled",
    val width: Dp = 400.dp,
    val height: Dp = 300.dp,

    val onStart: DialogController.() -> Unit = {},
    val onClose: () -> Unit = {},
) {

    @Composable
    abstract fun content(controller: DialogController)

}


abstract class DialogWindowWidget(
    name: String = UUID.randomUUID().toString(),

    // controller
    title: String = "Untitled",
    width: Dp = 400.dp,
    height: Dp = 300.dp,

    onStart: DialogController.() -> Unit = {},
    onClose: () -> Unit = {},
) : DialogWidget(
    name, title, width, height, onStart, onClose
)

abstract class DialogOverlayWidget(
    name: String = UUID.randomUUID().toString(),

    // controller
    title: String = "Untitled",
    width: Dp = 400.dp,
    height: Dp = 300.dp,

    onStart: DialogController.() -> Unit = {},
    onClose: () -> Unit = {},
) : DialogWidget(
    name, title, width, height, onStart, onClose
)