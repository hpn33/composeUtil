package util.compose.dialog.ready

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import util.compose.dialog.DialogService
import util.compose.dialog.component.DialogController
import util.compose.dialog.component.DialogOverlayBase
import util.compose.dialog.dialog
import util.compose.dialog.model.DialogWidget
import util.compose.dialog.showOverlay


inline fun showDialogCallback(
    message: String,
    dialogService: DialogService = dialog,
    crossinline action: (Boolean) -> Unit
) {
    dialogService.showOverlay(QDialog(message) {
        action(it)
    })
}


class QDialog(
    private val message: String,
    private val modifier: Modifier = Modifier,
    private val act: (Boolean) -> Unit
) : DialogWidget() {

    @Composable
    override fun content(controller: DialogController) =

        DialogOverlayBase {
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