package util.composeUtil.dialog.ready

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import composeUtil.dialog.component.DialogController
import composeUtil.dialog.model.DialogWidget


class QDialog(
    private val message: String,
    private val modifier: Modifier = Modifier,
    private val act: (Boolean) -> Unit
) : DialogWidget() {

    @Composable
    override fun content(controller: DialogController) =
        Surface(
            elevation = 6.dp,
            modifier = Modifier.width(350.dp)
        ) {

            MessageView(message, modifier) {
                controller.close()
                act(it)
            }
        }

}


@Composable
private fun MessageView(
    message: String,
    modifier: Modifier = Modifier,
    act: (Boolean) -> Unit
) {


    Column(modifier.padding(10.dp)) {

        Text(
            message,
//                fontSize = 20.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.padding(5.dp).fillMaxWidth()
        )

        Row(
            Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OutlinedButton({ act(true) }) { Text("بلی") }

            Button({ act(false) }) { Text("خیر") }

        }
    }


}