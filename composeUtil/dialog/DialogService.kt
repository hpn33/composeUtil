package composeUtil.dialog

import androidx.compose.runtime.mutableStateListOf
import composeUtil.dialog.component.DialogController
import composeUtil.dialog.model.DialogWidget
import composeUtil.dialog.util.toOverlayWidget
import composeUtil.dialog.util.toWindowWidget


object DialogService {

    val dialogStack = mutableStateListOf<DialogWidget>()
    private var dialogControllerStack = mutableListOf<DialogController>()

    fun openDialog(
        dialogWidget: DialogWidget,
        onStart: DialogController.() -> Unit,
        onClose: () -> Unit,
    ) {

        // TODO: problem:  not work if launch same dialog in square
        if (dialogStack.lastOrNull()?.name == dialogWidget.name) {
            return
        }

        dialogStack.add(dialogWidget)


        dialogWidget.getController()
            .apply {
                dialogWidget.onStart(this)

                this.onStart()
                this.onClose = onClose
            }

    }

    fun closeDialog() {

        dialogControllerStack.removeLastOrNull()
        dialogStack.removeLastOrNull()
            ?.also { println("Dialog Removed: ${it.name}") }

    }

    fun DialogWidget.getController(): DialogController {

        ifNotExistMakeController()

        // find stack index of dialog
        val stackLevel = dialogStack.indexOfLast { it.name == name }

        return dialogControllerStack[stackLevel]
    }

    private fun ifNotExistMakeController() {

        if (dialogStack.size == dialogControllerStack.size) {
            return
        }

        val currentDialog = dialogStack.last()
        val dialogController = DialogController(
            currentDialog.title,
            currentDialog.width,
            currentDialog.height,
            currentDialog.onClose,
        )

        dialogControllerStack.add(dialogController)
    }

}


typealias DialogWrapper = DialogService

val dialog: DialogWrapper
    get() = DialogService

fun DialogWrapper.showOverlay(

    dialogWidget: DialogWidget,
    onStart: DialogController.() -> Unit = {},
    onClose: () -> Unit = {},

    ) = openDialog(dialogWidget.toOverlayWidget(), onStart, onClose)

infix fun DialogWrapper.showOverlay(dialogWidget: DialogWidget) =
    openDialog(dialogWidget.toOverlayWidget(), {}, {})


fun DialogWrapper.showWindow(

    dialogWidget: DialogWidget,
    onStart: DialogController.() -> Unit = {},
    onClose: () -> Unit = {},

    ) = openDialog(dialogWidget.toWindowWidget(), onStart, onClose)


infix fun DialogWrapper.showWindow(dialogWidget: DialogWidget) =
    openDialog(dialogWidget.toWindowWidget(), {}, {})

fun DialogWrapper.close() = closeDialog()
