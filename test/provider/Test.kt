package test.provider

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import util.tool.provider.ProviderScope
import util.tool.provider.provider.normal.Provider
import util.tool.provider.use.useProvider
import util.tool.provider.use.useProviderAccess
//import util.tool.provider.use.useProviderLocal

val indexProvider = Provider { 0 }
val dIndexProvider = Provider {

    val a = get(indexProvider)

    a * 2

}

@Composable
fun ProviderTest() {

//    ProviderUse()

    ProviderTodoView()
}

@Composable
fun ProviderUse() {

    ProviderScope {
        println("---------- start ( properties )")

        var index by useProvider(indexProvider)
        val dIndex by useProvider(dIndexProvider)

        println("---------- ready ( ui )")

        Column {

            Text("$index")
            Text("$dIndex")

            Button({
                index += 1
            }) {
                Text("+")
            }
        }

        println("---------- end ( wait for actions )")

    }


}

//@Composable
//fun ProviderUseLocal() {
//
//    ProviderScope {
//        println("---------- start ( properties )")
//
//        var index by useProviderLocal(indexProvider)
//        val dIndex by useProvider(dIndexProvider)
//
//        println("---------- ready ( ui )")
//
//        Column {
//
//            Text("$index")
//            Text("$dIndex")
//
//            Button({
//                index += 1
//            }) {
//                Text("+")
//            }
//        }
//
//        println("---------- end ( wait for actions )")
//
//    }
//
//
//}


@Composable
fun ProviderAccess() {

    ProviderScope {
        println("---------- start ( properties )")

        var index by useProviderAccess(indexProvider)

        println("---------- ready ( ui )")

        Column {

            Text("$index")

            Button({
                index += 1
            }) {
                Text("+")
            }
        }

        println("---------- end ( wait for actions )")

    }


}


