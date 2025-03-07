package test.provider

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import util.compose.state.useState
import util.tool.provider.provider.normal.Provider
import util.tool.provider.provider.compose.ProviderCompose
import util.tool.provider.use.useProvider


val counterProvider = ProviderCompose("counter1", 0) {

    var a by useState(0)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)

            a++
        }
    }

    a
}

val counter2Provider = ProviderCompose("counter2", 0) {

    var a by useState(0)

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)

            a++
        }
    }

    a
}


val mulP = Provider("multiply") {
    val c1 = get(counterProvider)
    val c2 = get(counter2Provider)

    c1 + c2
}

@Composable
fun ProviderTestCompose() {

//    val counter by useProvider(counterProvider)
//    val counter2 by useProvider(counter2Provider)

    val mul by useProvider(mulP)



    Column {
//        Text(counter.toString())
//        Text(counter2.toString())

        Text(mul.toString())
    }

}
