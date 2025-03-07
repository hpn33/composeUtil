package util.compose.basicForm

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusRequester
//import mushroom_crm.composeapp.generated.resources.Res
//import mushroom_crm.composeapp.generated.resources.price
import org.jetbrains.compose.resources.stringResource


//@Composable
//fun PriceField(modifier: Modifier = Modifier) {
//
//    TextField(
//        amount?.toString() ?: "",
//        {
//
//            if (it.isEmpty())
//                amount = null
//
//            try {
//                val n = it.toLong()
//
//                amount = n
//
//            } catch (_: NumberFormatException) {
//
//            } catch (_: Exception) {
//
//            }
//        },
//        label = { Text(stringResource(Res.string.price)) },
//        modifier = modifier,
//        singleLine = true,
//        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//        keyboardActions = KeyboardActions(onNext = {
//            focusManager.moveFocus(FocusDirection.Down)
//        })
//    )
//
//}