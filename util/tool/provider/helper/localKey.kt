package util.tool.provider.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember


@Composable
fun rememberLocalProviderKey(key: Any) = remember(key) { LocalProviderKey.make() }

data class IdKey(val value: Int)

object LocalProviderKey {

    private var counter = 0

    fun make() = IdKey(counter++).hashCode()
}