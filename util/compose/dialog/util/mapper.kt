package util.compose.dialog.util

import androidx.compose.runtime.Composable
import util.compose.dialog.DialogService
import util.compose.dialog.component.DialogController
import util.compose.dialog.model.DialogData
import util.compose.dialog.model.DialogOverlayWidget
import util.compose.dialog.model.DialogWidget
import util.compose.dialog.model.DialogWindowWidget


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