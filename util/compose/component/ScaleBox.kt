package util.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.onSizeChanged
import util.compose.state.useState


@Composable
inline fun WidthScaleBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {


    var frameWidth by useState(0f)

    var contentWidth by useState(0f)
    var contentHeight by useState(0f)

    val scaleX = (frameWidth / contentWidth).let { if (it.isInfinite() || it.isNaN()) 1f else it }
    val ratio = (contentWidth / contentHeight).let { if (it.isInfinite() || it.isNaN()) 1f else it }

//    println("//////////////////")
//    println("frame: $frameWidth x $frameHeight")
//    println("content: $contentWidth x $contentHeight ( x${scaleX} )")
//    println("scale: x${scaleX}")
//    println("final: ${contentWidth * scaleX} x ${contentHeight * scaleX}")


    Box(
        modifier
            .fillMaxWidth()
            .onSizeChanged {

                frameWidth = it.width.toFloat()

            }
    ) {


        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(ratio)
        ) {

            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {


                Box(Modifier.scale(scaleX)) {
                    Box(Modifier.wrapContentSize(unbounded = true)) {

                        Box(
                            Modifier
                                .onSizeChanged {

                                    contentWidth = it.width.toFloat()
                                    contentHeight = it.height.toFloat()
                                }
                        ) {
                            content()
                        }
                    }
                }
            }
        }
    }
}


@Composable
inline fun HeightScaleBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {


    var frameHeigh by useState(0f)

    var contentWidth by useState(0f)
    var contentHeight by useState(0f)

    val scaleX = (frameHeigh / contentHeight).let { if (it.isInfinite() || it.isNaN()) 1f else it }
    val ratio = (contentWidth / contentHeight).let { if (it.isInfinite() || it.isNaN()) 1f else it }

//    println("//////////////////")
//    println("frame: $frameWidth x $frameHeight")
//    println("content: $contentWidth x $contentHeight ( x${scaleX} )")
//    println("scale: x${scaleX}")
//    println("final: ${contentWidth * scaleX} x ${contentHeight * scaleX}")


    Box(
        modifier
            .fillMaxHeight()
            .onSizeChanged {

                frameHeigh = it.height.toFloat()

            }
    ) {


        Box(
            Modifier
                .fillMaxHeight()
                .aspectRatio(ratio)
        ) {

            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {


                Box(Modifier.scale(scaleX)) {
                    Box(Modifier.wrapContentSize(unbounded = true)) {

                        Box(
                            Modifier
                                .onSizeChanged {

                                    contentWidth = it.width.toFloat()
                                    contentHeight = it.height.toFloat()
                                }
                        ) {
                            content()
                        }
                    }
                }
            }
        }
    }
}
