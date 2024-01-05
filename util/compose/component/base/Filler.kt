package util.compose.component.base

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
    Width(size)
}

@Composable
inline fun ColumnScope.Space(size: Int = 0) {
    Height(size)
}

@Composable
inline fun Width(size: Int = 0) {
    Spacer(Modifier.width(size.dp))
}

@Composable
inline fun Height(size: Int = 0) {
    Spacer(Modifier.height(size.dp))
}