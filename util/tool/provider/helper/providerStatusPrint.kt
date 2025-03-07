package util.tool.provider.helper

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import util.tool.provider.ProviderRef
import util.tool.provider.use.useProvider

@Composable
fun ProviderHelper.providerStatusPrint() {

    val ref = ProviderRef()

//    LaunchedEffect(Unit) {
//        val size = ref.providerHolderDB.holderList.groupBy { it.valueType }
//            .map {
//                "${it.key}:${it.value.size}"
//            }
//
//        println(size)
//
//    }

    val scopeTracker by useProvider(providerProviderScopeTracker)

    LaunchedEffect(Unit) {
        var prv = -1
//        var prvList = listOf("")
        var prvList: Map<String, List<String?>> = mapOf("" to listOf(""))


        while (true) {

            val bb = ArrayList<String>()

            val size2 = ref.providerHolderDB.holderList
                .groupBy {
                    val objectType = it.value!!::class.simpleName

//                    "${it.key} ${objectType.toString()}"
                    objectType.toString()
                }
                .mapNotNull {


                    val eachProvider = it.value.groupBy { it.key }
                        .map {


                            val locals =
                                it.value.map { holder ->
                                    "${holder.isActive(scopeTracker.currentScopeKey ?: "")}:" +
                                            holder.locals.toList().toString()
                                }
//                            .mapNotNull { it.activeScope.value?.let { scope -> it.locals[scope] } }
//                            .flatten()

                            if (locals.isEmpty())
                                null
                            else
                                "${it.key}: ${locals} "
                        }


                    it.key to eachProvider
                }.toMap()


            if (size2 != prvList) {

                println("{ ${size2.map { it.value }.flatten().size}")
                size2.forEach {
                    println("\t" + it.key + ":${it.value.size}")
                    it.value
                        .forEach { println("\t\t" + it) }
                }
                println("}")

                prvList = size2
            }


            val size = ref.providerHolderDB.holderList.size

            if (size != prv) {
                println(size)
                prv = size
            }

            delay(1000)
        }
    }

}
