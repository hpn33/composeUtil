package composeUtil.dialog.component

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import composeUtil.dialog.DialogService


class DialogController(
    var title: String = "",
    var width: Dp = 400.dp,
    var height: Dp = 300.dp,
    var onClose: () -> Unit = {},
) {

    fun close() {

        onClose()
        DialogService.closeDialog()

    }


}
