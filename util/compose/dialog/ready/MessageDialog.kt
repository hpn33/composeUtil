package util.compose.dialog.ready

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import util.compose.dialog.component.DialogController
import util.compose.dialog.model.DialogWidget


class MessageDialog(
    private val message: String,
    private val modifier: Modifier = Modifier,
    private val act: () -> Unit = {}
) : DialogWidget() {

    @Composable
    override fun content(controller: DialogController) =
        Box(Modifier.width(350.dp)) {
            MessageView(message) {
                controller.close()
                act()
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
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

//                OutlinedButton({ act(true) }) { Text("تایید") }

            Button({ act(false) }) { Text("تایید") }

        }
    }

}