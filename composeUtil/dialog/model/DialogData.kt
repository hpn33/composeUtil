package composeUtil.dialog.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composeUtil.dialog.component.DialogController
import java.util.*

data class DialogData(
    val name: String = UUID.randomUUID().toString(),

    // controller
    val title: String = "Untitled",
    val width: Dp = 400.dp,
    val height: Dp = 300.dp,

    val onStart: DialogController.() -> Unit = {},
    val onClose: () -> Unit = {},

    val content: DialogContentWithController,
)

typealias DialogContentWithController = @Composable (dialogController: DialogController) -> Unit
