package util.compose.component.base

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
inline fun VerticalDivider() {

    Divider(modifier = Modifier.width(1.dp).fillMaxHeight())

}