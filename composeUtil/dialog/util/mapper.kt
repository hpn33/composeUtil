package composeUtil.dialog.util

import androidx.compose.runtime.Composable
import composeUtil.dialog.DialogService
import composeUtil.dialog.component.DialogController
import composeUtil.dialog.model.DialogData
import composeUtil.dialog.model.DialogOverlayWidget
import composeUtil.dialog.model.DialogWidget
import composeUtil.dialog.model.DialogWindowWidget


fun DialogWidget.showDialogWindow(
    onStart: DialogController.() -> Unit = {},
    onClose: () -> Unit = {},
) {
    DialogService.openDialog(this.toWindowWidget(), onStart, onClose)
}

fun DialogWidget.showDialogOverlay(
    onStart: DialogController.() -> Unit = {},
    onClose: () -> Unit = {},
) {
    DialogService.openDialog(this.toOverlayWidget(), onStart, onClose)
}


fun DialogWidget.toWindowWidget(): DialogWindowWidget {
    return object : DialogWindowWidget(
        name, title, width, height, onStart, onClose
    ) {
        @Composable
        override fun content(controller: DialogController) =
            this@toWindowWidget.content(controller)

    }

}

fun DialogWidget.toOverlayWidget(): DialogOverlayWidget {
    return object : DialogOverlayWidget(
        name, title, width, height, onStart, onClose
    ) {
        @Composable
        override fun content(controller: DialogController) =
            this@toOverlayWidget.content(controller)

    }

}


fun DialogWidget.toDialogData() =
    DialogData(
        name, title, width, height, onStart, onClose, {}
    )