package util.compose.component.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RowScope.Weight(weight: Float = 1f, content: @Composable () -> Unit = {}) {

    if (weight != 0f)
        Box(Modifier.weight(weight)) {
            content()
        }

}

@Composable
fun RowScope.WeightCenter(weight: Float = 1f, content: @Composable () -> Unit) {

    Weight(weight) {
        RowFillCenter {
            content()
        }
    }

}


@Composable
fun ColumnScope.Weight(weight: Float = 1f, content: @Composable () -> Unit = {}) {

    if (weight != 0f)
        Box(Modifier.weight(weight)) {
            content()
        }

}

@Composable
fun ColumnScope.WeightCenter(weight: Float = 1f, content: @Composable () -> Unit) {

    Weight(weight) {
        ColumnFillCenter {
            content()
        }
    }
}