package util.composeUtil.component.base

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import javax.swing.Box.Filler

@Composable
inline fun RowScope.Filler() {
    Spacer(Modifier.weight(1f))
}


@Composable
inline fun ColumnScope.Filler() {
    Spacer(Modifier.weight(1f))
}


@Composable
inline fun RowScope.Space(size: Int = 0) {
    Spacer(Modifier.width(size.dp))
}

@Composable
inline fun ColumnScope.Space(size: Int = 0) {
    Spacer(Modifier.height(size.dp))
}