package util.composeUtil.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import composeUtil.state.useState
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

// content need to set size, height, width modifier
// not work
@Deprecated("not work")
@Composable
inline fun AutoScaleBox(
    modifier: Modifier = Modifier,
//    keepRatio: Boolean = true,
    content: @Composable () -> Unit,
) {


//    var cached by useState(false)
//    var cacheContentWidth by useState(0f)
//    var cacheContentHeight by useState(0f)

    var wantedWidth by useState(0f)
    var wantedHeight by useState(0f)

    var contentWidth by useState(0f)
    var contentHeight by useState(0f)

//    var scaleX by useState(1f)
//    var scaleY by useState(1f)
    val scaleX = (wantedWidth / contentWidth).let { if (it.isInfinite() || it.isNaN()) 1f else it }
    val scaleY = (wantedHeight / contentHeight).let { if (it.isInfinite() || it.isNaN()) 1f else it }


//    LaunchedEffect(cacheContentWidth == 0f, cacheContentHeight == 0f) {
//        delay(500)
//
//        if (cacheContentWidth == 0f) {
//            cacheContentWidth = contentWidth
//        }
//
//        if (cacheContentHeight == 0f) {
//            cacheContentHeight = contentHeight
//        }
//
//        scaleX = wantedWidth / cacheContentWidth
//        scaleY = wantedHeight / cacheContentHeight
//
//        if (keepRatio) {
//            if (scaleX < scaleY) {
//                scaleY = scaleX
//            } else if (scaleY < scaleX) {
//                scaleX = scaleY
//            }
//        }
//
//        cached = true
//    }

//    LaunchedEffect(wantedWidth, wantedHeight) {
//        if (cached) {
//            scaleX = wantedWidth / cacheContentWidth
//            scaleY = wantedHeight / cacheContentHeight
//
//            println("$scaleX $scaleY")
//            if (keepRatio) {
//                if (scaleX < scaleY) {
//                    scaleY = scaleX
//                } else if (scaleY < scaleX) {
//                    scaleX = scaleY
//                }
//            }
//
//        }
//
//    }

//    println(wantedWidth)
//    println("$contentWidth")
//    println("$scaleX (${contentWidth * scaleX})")


    Box(
        modifier
            .fillMaxSize()
//            .background(Color.Red)
            .onSizeChanged {
                wantedWidth = it.width.toFloat()
                wantedHeight = it.height.toFloat()
            }
    ) {

        Box(
            Modifier.size(
                (contentWidth * scaleX).dp,
                (contentHeight * scaleY).dp
            )
        ) {
            Box(
                Modifier
                    .wrapContentSize(
//                        align = Alignment.TopStart,
                        unbounded = true
                    )
                    .scale(
                        scaleX,
                        scaleY
                    )
//                    .background(Color.Blue)
            ) {

                Box(
                    Modifier
//                        .background(Color.Yellow)
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

@Composable
inline fun WidthScaleBox(
    modifier: Modifier = Modifier,
    cacheDelay: Duration = 0.seconds,
    content: @Composable () -> Unit,
) {

//    var cached by useState(false)
//    var cacheContentWidth by useState(0f)


    var frameWidth by useState(0f)

    var contentWidth by useState(0f)
    var contentHeight by useState(0f)

//    var scaleX by useState(1f)
    val scaleX = (frameWidth / contentWidth).let { if (it.isInfinite() || it.isNaN()) 1f else it }

//    println("//////////////////")
//    println("frame: " + frameWidth)
//    println("content: " + contentWidth)
//    println("scale: " + scaleX)

//    LaunchedEffect(cacheContentWidth == 0f) {
////        delay(cacheDelay)
//
//        if (cacheContentWidth == 0f) {
//            cacheContentWidth = contentWidth
//        }
//
//
//        scaleX = wantedWidth / cacheContentWidth
//        cached = true
//    }

//    LaunchedEffect(wantedWidth) {
//        if (cached) {
//            scaleX = wantedWidth / cacheContentWidth
//        }
//
//    }


    Box(
        modifier
            .fillMaxSize()
//            .background(Color.Red)
            .onSizeChanged {
                frameWidth = it.width.toFloat()
            }
    ) {

        Box(
            Modifier.size(
                (contentWidth * scaleX).dp,
                (contentHeight * scaleX).dp
            )
//                    .background(Color.Green)
            ,
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .scale(scaleX)
                    .wrapContentSize(
                        unbounded = true,
                    )
//                    .background(Color.Blue)
            ) {

                Box(
                    Modifier
//                        .background(Color.Yellow)
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


@Composable
inline fun HeightScaleBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

//    var cached by useState(false)
//    var cacheContentHeight by useState(0f)


    var wantedHeight by useState(0f)

    var contentWidth by useState(0f)
    var contentHeight by useState(0f)

//    var scaleY by useState(1f)
    val scaleY = (wantedHeight / contentHeight).let { if (it.isInfinite() || it.isNaN()) 1f else it }

//    LaunchedEffect(cacheContentHeight == 0f) {
//        delay(500)
//
//        if (cacheContentHeight == 0f) {
//            cacheContentHeight = contentHeight
//        }
//
//
//        scaleY = wantedHeight / cacheContentHeight
//        cached = true
//    }
//
//    LaunchedEffect(wantedHeight) {
//        if (cached) {
//            scaleY = wantedHeight / cacheContentHeight
//        }
//
//    }


    Box(
        modifier
            .fillMaxSize()
//            .background(Color.Red)
            .onSizeChanged {
                wantedHeight = it.width.toFloat()
            }
    ) {

        Box(
            Modifier.size(
                (contentWidth * scaleY).dp,
                (contentHeight * scaleY).dp
            ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .wrapContentSize(
                        unbounded = true
                    )
                    .scale(scaleY)
//                    .background(Color.Blue)
            ) {

                Box(
                    Modifier
//                        .background(Color.Yellow)
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
