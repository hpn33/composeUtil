package util.compose.component.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RowScope.Weight(weight: Float = 1f, content: @Composable () -> Unit) {

    Box(Modifier.weight(weight)) {
        content()
    }

}

@Composable
fun ColumnScope.Weight(weight: Float = 1f, content: @Composable () -> Unit) {

    Box(Modifier.weight(weight)) {
        content()
    }

}