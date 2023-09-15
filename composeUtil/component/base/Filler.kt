package composeUtil.component.base

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import javax.swing.Box.Filler

@Composable
inline fun RowScope.Filler() {
    Spacer(Modifier.weight(1f))
}

@Composable
inline fun ColumnScope.Filler() {
    Spacer(Modifier.weight(1f))
}