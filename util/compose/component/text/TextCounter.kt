package util.compose.component.text

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import util.compose.component.base.RowCenter
import util.compose.layout.LTR
import util.compose.state.useState


private data class Digit(
    val digitChar: Char,
    val fullNumber: Int,
    val place: Int
) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Digit -> digitChar == other.digitChar
            else -> super.equals(other)
        }
    }

    operator fun compareTo(other: Digit): Int {
        return fullNumber.compareTo(other.fullNumber)
    }

}


//data class TextStyle(
//    val fontSize: TextUnit = TextUnit.Unspecified,
//    val fontWeight: FontWeight? = null
//)


@Composable
fun TextCounter(
    number: Int,
    style: TextStyle = LocalTextStyle.current,
    textHandler: ((Char) -> String)? = null
) {

    var oldNumber by useState(number)
    var isGoUp by useState(false)

    LaunchedEffect(number) {

        if (number == oldNumber) return@LaunchedEffect

        isGoUp = number > oldNumber

        oldNumber = number

    }


    val inputNumber by animateFloatAsState(number.toFloat())

    val numberString = inputNumber.toInt().toString().mapIndexed { index, c -> Digit(c, number, index) }


    LTR {
        RowCenter(Modifier.animateContentSize()) {

            numberString.forEach {

                TextElement(it, isGoUp, style, textHandler)

            }
        }
    }

}

@Composable
private inline fun TextElement(
    number: Digit,
    isGoUp: Boolean,
    style: TextStyle,
    noinline textHandler: ((Char) -> String)? = null
) {

    AnimatedContent(
        number,
        transitionSpec = {

            if (isGoUp) {
                slideInVertically { it } togetherWith slideOutVertically { -it }
            } else {
                slideInVertically { -it } togetherWith slideOutVertically { it }
            }
        }
    ) {

        val handledText =
            if (textHandler == null)
                it.digitChar.toString()
            else
                textHandler(it.digitChar)

        Text(
            handledText,
            style = style
        )

    }


}