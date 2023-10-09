package util.compose.dialog.model

import androidx.compose.runtime.Composable
import util.compose.dialog.component.DialogController

inline fun makeDialog(title: String, crossinline content: @Composable (DialogController) -> Unit) =
    object : DialogWidget(title = title) {
        @Composable
        override fun content(controller: DialogController) = content(controller)

    }