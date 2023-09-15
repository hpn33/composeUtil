package composeUtil.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection


@Composable
fun LayoutDirection(direction: LayoutDirection = LayoutDirection.Ltr, content: @Composable () -> Unit) {

    CompositionLocalProvider(LocalLayoutDirection provides direction) {
        content()
    }

}


@Composable
fun RTL(content: @Composable () -> Unit) {
    LayoutDirection(LayoutDirection.Rtl) {

        content()

    }

}

@Composable
fun LTR(content: @Composable () -> Unit) {
    LayoutDirection(LayoutDirection.Ltr) {

        content()

    }

}