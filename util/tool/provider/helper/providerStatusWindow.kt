package util.tool.provider.helper

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Window
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import util.compose.component.base.Weight
import util.kot.collect.include
import util.tool.provider.ProviderRef
import util.tool.provider.use.useProvider


// all list
// active list


@Composable
fun ProviderHelper.providerStatusWindow() {

    // todo: debug
//    Window({}) {
        providerStatusStateful()
//    }

}

@Composable
private fun providerStatusStateful() {


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

    var sizeList by remember { mutableStateOf<List<ProviderStatusData>>(listOf()) }



    LaunchedEffect(Unit) {
        var prv = -1


        while (true) {

            val activeScope = scopeTracker.activeScope


            val size2 = ref.providerHolderDB.holderList
                .map {

                    val objectType = it.value::class.simpleName

                    val isActive =
//                        if (activeScope == null)
//                            false
//                        else if (!activeScope.reactiveScope)
//                            true
//                        else
//                            it.isActive(activeScope.name)
                        it.isActive("")

                    val final = ProviderStatusData(
                        label = it.key,
                        dataType = objectType.toString(),
                        active = isActive,
                        location = it.locals,
//                        updateAt = Clock.System.now().toEpochMilliseconds()
                    )

                    final
                }


            if (size2 != sizeList) {

                sizeList = size2

//                println("{ ${size2.map { it.value }.flatten().size}")
//                size2.forEach {
//                    println("\t" + it.key + ":${it.value.size}")
//                    it.value
//                        .forEach { println("\t\t" + it) }
//                }
//                println("}")

//                prvList = size2
            }


            val size = ref.providerHolderDB.holderList.size

            if (size != prv) {
                println(size)
                prv = size
            }

            delay(1000)
        }
    }



    providerStatusContent(sizeList, scopeTracker)
}

//enum class Orientation {
//    Label,
//    DataType,
//    Active,
////    Location
//}

@Composable
private fun providerStatusContent(
    providerDataList: List<ProviderStatusData>,
    tracker: ProviderScopeTracker
) {


//    var orientation by useState(Orientation.Label)

    val sizeList = providerDataList
//        .sortedByDescending { it.updateAt }
//        .sortedByDescending { it.active }


//    val sizeList by remember(orientation, providerDataList) {
//        derivedStateOf {
////            providerDataList.groupBy {
////                when (orientation) {
////                    Orientation.Label -> it.label
////                    Orientation.DataType -> it.dataType
////                    Orientation.Active -> it.active.toString()
//////                    Orientation.Location -> it.location.toString()
////                }
////            }
//
//
//            providerDataList.sortedBy {
//                when (orientation) {
//                    Orientation.Label -> it.label
//                    Orientation.DataType -> it.dataType
//                    Orientation.Active -> it.active.toString()
////                    Orientation.Location -> it.location.toString()
//                }
//            }
//
//        }
//    }


    Column(
//        Modifier.verticalScroll(rememberScrollState())
    ) {

//        RowFillCenter(modifier = Modifier.padding(32.dp)) {
//            Text("Count: ${sizeList.size} --> ${sizeList.map { it.value }.flatten().size.toString()}")

        Column(modifier = Modifier.fillMaxWidth().padding(32.dp)) {

            Text("Count: ${sizeList.size} ")

//            Width(64)

            Row {


                Text("Scope: ")
//                Text(tracker.stack.joinToString(" ") {
//                 if (it == tracker.currentScopeKey)
//                    "<${it}>"
//                    else
//                        it
//                }

                Text(tracker.currentScopeKey ?: "null")

            }
            Text(tracker.list.joinToString("\n") { it.toString() })
//                Column {
//
////                    Text(tracker.currentScopeKeyNotFreeScope)
//                }
        }


//            Text("Orientation: ")


//            Orientation.entries.forEach {
//                Button(
//                    {
//                        orientation = it
//                    },
//                    modifier = Modifier.padding(8.dp)
//                ) {
//                    Text(it.name.toString())
//                }
//            }

//        }


        Column(Modifier.verticalScroll(rememberScrollState())) {

            sizeList.forEach {

                Row(
                    Modifier
                        .clickable { }
                        .padding(bottom = 8.dp)
                ) {
                    Weight { Text(it.label) }
                    Weight { Text(it.dataType) }
                    Weight(.5f) { Text(it.active.toString()) }
                    Weight(2f) {
                        Column {
                            it.location
                                .toList()
                                .sortedBy { it.second.isEmpty() }
                                .toMap()
                                .forEach {

                                    val isActive = it.value.isNotEmpty()
                                    val color = if (isActive) Color.Unspecified else Color.LightGray

                                    Row {
                                        Text(
//                                            it.key.toString() + "(${it.value.size}):" + it.value.toString(),
                                            it.key.toString() + "(${it.value.size}):",
                                            color = color
                                        )

                                        Column {
                                            it.value.forEach {
                                                Text(it.toString(), color = color)
                                            }
                                        }

                                    }
                                }
                        }
                    }
                }
            }

        }

//        when (orientation) {
//            Orientation.Label -> ViewLabel(sizeList.toList() as List<Pair<String, List<ProviderStatusData>>>)
//            Orientation.DataType -> ViewLabel(sizeList.toList() as List<Pair<String, List<ProviderStatusData>>>)
//            Orientation.Active -> ViewLabel(sizeList.toList() as List<Pair<String, List<ProviderStatusData>>>)
////            Orientation.Location -> ViewLabel(sizeList.toList() as List<Pair<String, List<ProviderStatusData>>>)
//        }


//
//        sizeList.forEach {
//
//            Text(it.key + ":${it.value.size}", modifier = Modifier.padding(start = 32.dp).clickable { })
//
//            it.value
//                .forEach {
//                    Text(it.toString(), modifier = Modifier.padding(start = 64.dp).clickable { })
//                }
//        }

    }


}


//@Composable
//fun ViewLabel(sizeList: List<Pair<String, List<ProviderStatusData>>>) {
//
////    val sizeList = providerDataList.groupBy { it.label }
//
//    sizeList.forEach { (key, value) ->
//
//        Text(key + ":${value.size}", modifier = Modifier.padding(start = 32.dp).clickable { })
//
//        value
//            .forEach {
//                Text(it.toString(), modifier = Modifier.padding(start = 64.dp).clickable { })
//            }
//    }
//
//}