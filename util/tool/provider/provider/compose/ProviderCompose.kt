package util.tool.provider.provider.compose

import androidx.compose.runtime.Composable
import util.tool.provider.ProviderService
import util.tool.provider.ProviderType
import util.tool.provider.provider.ProviderBaseReady


class ProviderCompose<T>(
    label: String = "",
    val default: T,
    val builder: @Composable () -> T,
) : ProviderBaseReady<T, T, ProviderHolderCompose>(label = label, type = ProviderType.Compose) {

    override fun ProviderService.makeHolder(): ProviderHolderCompose {

        val p = ProviderHolderCompose(
            this@ProviderCompose.toString(),
            { this@ProviderCompose.builder() as Any },
        )

//        providerHolderDB.add(p)


        return p
    }

}