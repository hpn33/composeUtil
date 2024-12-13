package util.compose.dialog.ready

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import util.compose.dialog.component.DialogController
import util.compose.dialog.component.DialogOverlayBase
import util.compose.dialog.model.DialogWidget

// TODO: not work
class MenuDialog(val itemList: List<MenuItem>) : DialogWidget() {

    @Composable
    override fun content(controller: DialogController) {



        DialogOverlayBase {
            Surface {

                Column {

                    itemList.forEach {

                        Box(Modifier.clickable(onClick = it.action)) {
                            Text(it.title)
                        }


                    }
                }

            }
        }


    }

}

@Composable
fun MenuView() {

}


data class MenuItem(val title: String, val action: () -> Unit)