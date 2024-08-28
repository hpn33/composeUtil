package util.compose.orbitalHelper

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


class OrbitalTag(val content: @Composable () -> Unit) {

    companion object {
        fun <I> withInput(content: @Composable (I) -> Unit) = OrbitalTagInput(content)
    }

    @Composable
    fun callContent() {
        content()
    }


}

class OrbitalTagInput<I>(val content: @Composable (I) -> Unit) {

    @Composable
    fun callContent(input: () -> I) {
        content(input())
    }


}


val asd =
    OrbitalTag.withInput<String> { title ->
        Text(title)
    }


@Composable
fun asd2() {

    Column {
        orbitalUse(asd) { " hello " }
    }
}


@Composable
fun <I> orbitalUse(tag: OrbitalTagInput<I>, input: () -> I) {

    tag.callContent(input)

}
