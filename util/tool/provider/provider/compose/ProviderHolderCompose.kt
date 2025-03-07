package util.tool.provider.provider.compose


import androidx.compose.runtime.Composable
import util.tool.provider.ProviderType
import util.tool.provider.provider.ProviderHolderBase


class ProviderHolderCompose(
    key: String,
    val builder: @Composable () -> Any,
    state: Any? = null,
) : ProviderHolderBase(
    key = key,
    providerType = ProviderType.Compose,
    _value = state
) {

    override fun revalue() {

    }


}
