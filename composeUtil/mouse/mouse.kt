package composeUtil.mouse

//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.ExperimentalComposeUiApi
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.input.pointer.PointerEventType
//import androidx.compose.ui.input.pointer.onPointerEvent
//import composeUtil.klass.Position


//@Composable
//inline fun rememberMouseState(): MutableState<Position> =
//    remember { mutableStateOf(Offset.Zero) }
//
//
//@OptIn(ExperimentalComposeUiApi::class)
//fun Modifier.trackMouse(mousePosition: MutableState<Position>) =
//    onPointerEvent(PointerEventType.Move) {
//        mousePosition.value = it.changes[0].position
//    }