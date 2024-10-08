package test.provider

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import util.compose.provider.*
import util.service.provider.ProviderScope
import util.service.provider.provider.Provider
import util.service.provider.useProvider
import util.service.provider.useProviderAccess
import util.service.provider.useProviderLocal

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

@Composable
fun ProviderUseLocal() {

    ProviderScope {
        println("---------- start ( properties )")

        var index by useProviderLocal(indexProvider)
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


