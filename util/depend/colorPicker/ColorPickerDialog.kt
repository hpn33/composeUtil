package util.depend.colorPicker

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import util.color.ColorConvert
import util.color.Colors
import util.color.eyeContrast
import util.color.makeRandomColor
import util.color.util.toColorInt
import util.color.util.toComposeColor
import util.compose.component.base.*
import util.compose.dialog.component.DialogController
import util.compose.dialog.component.DialogPanel
import util.compose.dialog.model.DialogWidget
import util.compose.layout.LTR
import util.compose.modifier.clickableSilent
import util.compose.state.useState
import util.compose.ui.ux.size.round20d

@Composable
fun ColorPickerView(color: Color, action: (Color) -> Unit, onClose: () -> Unit) {


    val colorPickerController = rememberColorPickerController()
    var colorCode by useState(
        ColorConvert.integerToHex(color.toColorInt().value).substring(2, 8)
    )

    // TODO: fix this mass ( remove color code or make sync with color picker changes )
    LaunchedEffect(colorPickerController.selectedColor.value) {

        colorCode =
            ColorConvert.integerToHex(
                colorPickerController.selectedColor.value.toColorInt().value
            ).substring(2, 8)

//            ColorConvert.hexToInteger("ff$fixValue").toColorInt().toComposeColor()

    }


    Box(
        Modifier
            .clickableSilent { onClose() }
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        DialogPanel(Modifier.padding(30.dp)) {


            Column {

                RowFillCenter {

                    Text("ویرایش رنگ")
                    Filler()
                    IconButton({
                        colorPickerController.selectByColor(color, false)
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "")
                    }

                }

                Row {
                    Weight {
                        ColumnCenter {
                            Center(
                                Modifier.background(color).fillMaxWidth().height(40.dp)
                            ) {

                                Text("الان", color = color.eyeContrast())
                            }
                        }
                    }
                    Weight {
                        ColumnCenter {
                            Center(
                                Modifier
                                    .background(colorPickerController.selectedColor.value).fillMaxWidth()
                                    .height(40.dp)
                            ) {
                                Text("جدید", color = colorPickerController.selectedColor.value.eyeContrast())
                            }

                            Box(
                                Modifier
                                    .clip(round20d)
                                    .clickable {
                                        colorPickerController.selectByColor(makeRandomColor(), false)
                                    }
                                    .background(Colors.grey.shade100, round20d)
                                    .padding(5.dp)
                            ) {
                                RowCenter {
                                    Icon(Icons.Default.Refresh, contentDescription = "")
                                    Width(5)
                                    Text("اتفاقی")
                                }
                            }

                        }
                    }
                }

                Box(Modifier.padding(20.dp)) {
                    Column {


                        HsvColorPicker(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxWidth(),
                            initialColor = color,
                            controller = colorPickerController,
                            onColorChanged = { colorEnvelope: ColorEnvelope ->
                                // do something
                            }
                        )


                        BrightnessSlider(
                            Modifier.height(20.dp),
                            controller = colorPickerController,
                            initialColor = color
                        )

                    }

                }

                ColorCode(
                    colorCode
//                        colorPickerController.selectedColor.value
                ) {

                    colorCode = it

//                            ColorConvert.hexToInteger("ff$it").toColorInt().toComposeColor()

//                        colorPickerController.selectByColor(a, false)
                }


                RowFillCenter {
                    IconButton({ onClose() }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }

                    Filler()

                    IconButton({
                        action(colorPickerController.selectedColor.value)
                        onClose()
                    }) {
                        Icon(Icons.Default.Done, contentDescription = "Done")
                    }
                }

            }

        }
    }
}

@Composable
private fun ColorCode(colorCode: String, onColorChanged: (String) -> Unit) {
    RowFillCenter {
        Text("کد رنگ")

//            var colorCode by useState(
//                color,
//                ColorConvert.integerToHex(color.toColorInt().value).substring(2, 8)
//            )

        Box(modifier = Modifier.weight(1f)) {
            LTR {
                OutlinedTextField(
                    colorCode,
                    onValueChange = {

                        val itFix =
                            if (it.length > 6) {
                                it.substring(0, 6)
                            } else {
                                it
                            }

                        val fixValue =
                            if (itFix.length < 6) {
                                itFix.padEnd(6, '0')
                            } else if (itFix.isEmpty()) {
                                "000000"
                            } else {
                                "000000"
                            }

                        try {

//                                colorCode = itFix

                            val newColor = ColorConvert.hexToInteger("ff$fixValue").toColorInt().toComposeColor()

//                                            colorPickerController.selectByColor(
//                                                false
//                                            )

                            onColorChanged(itFix)
                        } catch (e: NumberFormatException) {

                        }


                    },

                    )
            }
        }

        Box(
            Modifier
                .background(
                    ColorConvert.hexToInteger(colorCode).toColorInt().toComposeColor()
                )
//                    .fillMaxWidth(.1f)
        )


    }

}

