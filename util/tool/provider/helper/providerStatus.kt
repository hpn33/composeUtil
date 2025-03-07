package util.tool.provider.helper


data class ProviderStatusData(
    val label: String,
    val dataType: String,
    val active: Boolean,
    val location: MutableMap<String, MutableList<Int>>,
//    val updateAt: Long,
)