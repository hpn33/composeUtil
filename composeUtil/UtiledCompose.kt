package composeUtil

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import composeUtil.dialog.DialogHost


@Composable
fun UtilizedCompose(content: @Composable () -> Unit) {

    // [x] can run dialog host
    // [ ] work with navigation

    Box {

        content()

        DialogHost()

    }


}