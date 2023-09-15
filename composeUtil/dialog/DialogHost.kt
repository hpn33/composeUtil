package composeUtil.dialog

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.*
import composeUtil.dialog.DialogService.getController
import composeUtil.dialog.component.DialogOverlayBase
import composeUtil.dialog.model.DialogOverlayWidget
import composeUtil.dialog.model.DialogWidget
import composeUtil.dialog.model.DialogWindowWidget


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

