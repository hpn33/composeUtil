package util.compose.component.chart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import util.color.Colors
import util.compose.component.base.Weight
import util.compose.ui.ux.size.round5d


@Composable
private inline fun ColumnScope.emptyWidget(value: Float) {
    if (value != 0f)
        Weight(value) { }
}

@Composable
private inline fun ColumnScope.fillWidget(value: Float, noinline content: @Composable () -> Unit) {
    if (value != 0f)
        Weight(value, content)
}


@Composable
fun ProgressBarVerticalSimple(
    value: Float = 0f,
    maxValue: Float = 1f,
    thickness: Int = 10,
    reverse: Boolean = false,
) {

    val fill = value
    val empty = maxValue - value


    Column(
        Modifier.background(Colors.grey.default.copy(.2f), round5d).fillMaxHeight().width(thickness.dp)
    ) {


        if (reverse) {
            fillWidget(fill) {
                Box(Modifier.background(Colors.blue.default.copy(.2f), round5d).fillMaxSize())
            }
            emptyWidget(empty)

        } else {

            emptyWidget(empty)
            fillWidget(fill) {
                Box(Modifier.background(Colors.blue.default.copy(.2f), round5d).fillMaxSize())
            }
        }
    }


}


@Composable
fun ProgressBarVertical(
    value: Float = 0f,
    maxValue: Float = 1f,
    background: Color = Colors.grey.default.copy(.2f),
    color: Color = Colors.blue.default,
    shape: Shape = round5d,
    reverse: Boolean = false
) {

    val fill = value
    val empty = maxValue - value


    Column(
        Modifier.background(background, shape).fillMaxHeight().width(10.dp)
    ) {

        if (reverse) {
            fillWidget(fill) {
                Box(Modifier.background(color, shape).fillMaxSize())
            }
            emptyWidget(empty)

        } else {

            emptyWidget(empty)
            fillWidget(fill) {
                Box(Modifier.background(color, shape).fillMaxSize())
            }
        }
    }
}


@Composable
fun ProgressBarVerticalWithContent(
    value: Float = 0f,
    maxValue: Float = 1f,
    thickness: Int = 10,
    background: Color = Colors.grey.default.copy(.2f),
    color: Color = Colors.blue.default,
    shape: Shape = round5d,
    reverse: Boolean = false,
    content: @Composable BoxScope.() -> Unit
) {

    val fill = value
    val empty = maxValue - value


    Column(
        Modifier.background(background, shape).fillMaxHeight().width(thickness.dp)
    ) {


        if (reverse) {
            fillWidget(fill) {
                Box(Modifier.background(color, shape).fillMaxSize()) {
                    content()
                }
            }
            emptyWidget(empty)

        } else {

            emptyWidget(empty)
            fillWidget(fill) {
                Box(Modifier.background(color, shape).fillMaxSize()) {
                    content()
                }
            }
        }
    }
}
