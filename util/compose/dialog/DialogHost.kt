package util.compose.dialog

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.*
import util.compose.dialog.DialogService.getController
import util.compose.dialog.component.DialogOverlayBase
import util.compose.dialog.model.DialogOverlayWidget
import util.compose.dialog.model.DialogWidget
import util.compose.dialog.model.DialogWindowWidget


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DialogHost() {
    // TODO: sometime two time component run(check dialog register)
    println("dialog ()")

    val dialogs = remember("stack") { DialogService.dialogStack }

    for (dialog in dialogs) {

        dialog.show()

    }

//    dialogs.lastOrNull()?.show()

}


@Composable
fun DialogWidget.show() {

    when (this) {
        is DialogOverlayWidget -> show()
        is DialogWindowWidget -> show()
        else -> {}
    }

}


@Composable
fun DialogWindowWidget.show() {

    println("dialog(WINDOW) :: $name")


    val controller = getController()
//    val dialogState = rememberDialogState(width = controller.width, height = controller.height)


    Dialog({}, DialogProperties()) {

    }

    Dialog(
        controller::close,
        DialogProperties()
//        state = dialogState,
//        title = controller.title,
    ) {

        content(controller)
    }

}


@Composable
fun DialogOverlayWidget.show() {

    println("dialog(OVER) :: $name")

    val controller = getController()

    Popup(
        alignment = Alignment.Center,
        onDismissRequest = controller::close,
        properties = PopupProperties(
            focusable = true
        ),
//        onPreviewKeyEvent = { false },
//        onKeyEvent = { false }
    ) {

        DialogOverlayBase {

            content(controller)

        }

    }

}

