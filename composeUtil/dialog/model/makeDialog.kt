package composeUtil.dialog.model

import androidx.compose.runtime.Composable
import composeUtil.dialog.component.DialogController

inline fun makeDialog(title: String, crossinline content: @Composable (DialogController) -> Unit) =
    object : DialogWidget(title = title) {
        @Composable
        override fun content(controller: DialogController) = content(controller)

    }