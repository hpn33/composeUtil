package util.compose.dialog.ready

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import hpn.routine.ui.dialog.colorPicker.ColorPickerView
import util.compose.dialog.component.DialogController
import util.compose.dialog.model.DialogWidget


class ColorPickerDialog(private val color: Color, val action: (Color) -> Unit) : DialogWidget() {

    @Composable
    override fun content(controller: DialogController) {

        ColorPickerView(color, action, controller::close)
    }

}