package util.compose.form

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import hpn.routine.ui.dialog.colorPicker.ColorPickerDialog
import util.color.Colors
import util.color.makeRandomColor
import util.color.util.toColorInt
import util.color.util.toComposeColor
import util.compose.component.base.*
import util.compose.dialog.dialog
import util.compose.dialog.showOverlay
import util.compose.state.*
import util.compose.ui.ux.size.round100p
import util.compose.ui.ux.size.round20d


/**
 * - [x] field
 *  - [ ] field config-setting
 * - [ ] action bar
 * - [ ] other data type
 * - [ ] style control
 * - [ ]
 */


typealias FormDataMap = MutableMap<String, Any>

class FormScope(private val signal: SignalSubscribe) {

    val dataMapDefault: FormDataMap = mutableMapOf()
    val dataMap: FormDataMap = mutableMapOf()


    @Composable
    fun textField(id: String, label: String = "text") {

        var state by useState(dataMap.getOrDefault(id, "") as String)

        signal.hook {
            state = dataMap[id] as String
        }

        LaunchedEffect(state) {
            dataMap[id] = state
        }

        OutlinedTextField(
            state,
            onValueChange = { state = it },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )

    }

    @Composable
    fun textMultilineField(id: String, label: String = "text") {

        var state by useState(dataMap.getOrDefault(id, "") as String)

        signal.hook {
            state = dataMap[id] as String
        }

        LaunchedEffect(state) {
            dataMap[id] = state
        }

        OutlinedTextField(
            state,
            onValueChange = { state = it },
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2,
            singleLine = false,
        )

    }


    @Composable
    fun booleanField(id: String, label: (Boolean) -> String = { "switch" }) {

        var state by useState(dataMap.getOrDefault(id, false) as Boolean)

        signal.hook {
            state = dataMap[id] as Boolean
        }

        LaunchedEffect(state) {
            dataMap[id] = state
        }

        val title = remember(state) { label(state) }

        Row(
            Modifier
                .clip(RoundedCornerShape(20.dp))
                .clickable { state = !state }
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(title)

            Switch(state, { state = it })
        }


    }

    @Composable
    fun colorField(id: String) {

        var state by useState(
            (dataMap.getOrDefault(id, 0xffffffff) as Int)
                .toColorInt().toComposeColor()
        )

        signal.hook {
            val a = dataMap[id] as Int

            state = a.toColorInt().toComposeColor()
        }

        LaunchedEffect(state) {
            dataMap[id] = state.toColorInt().value
        }

        RowFillCenter(Modifier.background(Colors.grey.shade100, round20d)) {

            RowCenter() {

                IconButton({
                    state = makeRandomColor()
                }) {
                    Icon(Icons.Outlined.Refresh, contentDescription = "refresh")
                }

                Box(Modifier.height(50.dp).padding(vertical = 5.dp)) {
                    VerticalDivider()
                }
            }


            RowFillCenter(
                Modifier
                    .weight(1f)
                    .clip(round20d)
                    .clickable {
                        dialog.showOverlay(ColorPickerDialog(color = state) {
                            state = it
                        })
                    }
            ) {

                Box(Modifier.size(50.dp), contentAlignment = Alignment.Center) {
                    Icon(Icons.Outlined.Edit, contentDescription = "edit")
                }

                Filler()

                Box(
                    Modifier.size(50.dp).padding(5.dp)
                        .background(state.toColorInt().toComposeColor(), round100p)
                )

            }


        }


    }

}

@Composable
fun FormBase(
    onInit: (FormDataMap) -> Unit = {},
    onDone: (FormDataMap) -> Unit = {},
    onRefresh: (FormDataMap) -> Unit = {},
    content: @Composable (FormScope.() -> Unit)
) {

//    val signal = Signal()
    val signal = useSignalSubscribe()
    val scopeManager = remember { FormScope(signal) }


    firstTime {
        onInit(scopeManager.dataMap)

        scopeManager.dataMapDefault.clear()
        scopeManager.dataMapDefault.putAll(scopeManager.dataMap)

    }


    Column {

        scopeManager.content()

        Row {
            IconButton({
                onDone(scopeManager.dataMap)

                scopeManager.dataMapDefault.clear()
                scopeManager.dataMapDefault.putAll(scopeManager.dataMap)
            }) {
                Icon(imageVector = Icons.Outlined.Done, contentDescription = "Done")
            }

            Filler()


            IconButton({
                onRefresh(scopeManager.dataMap)

                scopeManager.dataMap.clear()
                scopeManager.dataMap.putAll(scopeManager.dataMapDefault)
                signal.reflect()

            }) {
                Icon(imageVector = Icons.Outlined.Refresh, contentDescription = "Refresh")
            }
        }

    }
}