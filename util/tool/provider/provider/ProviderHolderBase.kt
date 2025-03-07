package util.tool.provider.provider

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import util.log.Logger.log
import util.tool.provider.ProviderType
import util.tool.provider.inProvider.ProviderWatchHolder

abstract class ProviderHolderBase(
    val key: String,
    val providerType: ProviderType,

    private var _value: Any? = null,

    val subscribe: MutableList<(Any) -> Unit> = mutableListOf(),
    val dependencies: MutableList<String> = mutableListOf(),
    val watchers: List<ProviderWatchHolder<Any>> = listOf(),
//    val localCompose: MutableList<@Composable () -> Any> = mutableListOf(),

    val locals: MutableMap<String, MutableList<Int>> = mutableMapOf(),
//    val activeScope: MutableState<String?> = mutableStateOf(null),
//    val isActive: MutableState<Boolean> = mutableStateOf(false)

//    var updateAt: Long
) {

    val valueNullable: Any? get() = _value

    var value: Any
        get() = _value!!
        set(value) {
            _value = value

            subscribe.forEach { it.invoke(value) }
        }

    fun isActive(activeScopeName: String): Boolean {

//        if (locals[FreeScope]?.isNotEmpty() == true) {
//            return true
//        }

        return locals.values.any { it.isNotEmpty() }


        return locals[activeScopeName]?.isNotEmpty() ?: false
    }


//    val typeString: String = Type::class.toString()

    abstract fun revalue()


    fun load() {

        if (_value != null) return

        log("[ProviderHolder] $key [load]")

        revalue()

    }

}
