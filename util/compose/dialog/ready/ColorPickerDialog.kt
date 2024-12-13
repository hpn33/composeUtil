package util.compose.dialog.ready

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import util.compose.dialog.component.DialogController
import util.compose.dialog.component.DialogOverlayBase
import util.compose.dialog.model.DialogWidget
import util.depend.colorPicker.ColorPickerView


class ColorPickerDialog(private val color: Color, val action: (Color) -> Unit) : DialogWidget() {

    @Composable
    override fun content(controller: DialogController) {


        DialogOverlayBase {
            ColorPickerView(color, action, controller::close)
        }
    }

}