package test.provider

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import util.tool.provider.ProviderScope
import util.tool.provider.helper.ProviderHelper
import util.tool.provider.provider.normal.Provider
import util.tool.provider.helper.providerStatusPrint
import util.tool.provider.use.useProvider


@Composable
fun providerTestLocalHolder(): Unit {

    locationHolder()

}


private val p1 = Provider { 1 }
private val p2 = Provider { get(p1) }


@Composable
private fun locationHolder(modifier: Modifier = Modifier) {


    // watch all providers
    // check change provider by connection

    // connect p1 to p2 ( p2 react to p1 )
    // change p1
    // if p2 was use p2 change and react


    ProviderScope {

        ProviderHelper.providerStatusPrint()

        Box {

            val p1use by useProvider(p1)

            Column(modifier = modifier) {

                locationA1()

                if (p1use % 2 == 0)
                    locationA2()

            }
        }
    }

}



@Composable
fun locationA1(modifier: Modifier = Modifier) {

    var p1use by useProvider(p1)

    Column {
        Text(
            text = "A1 : $p1use",
            Modifier
                .clickable {
                    p1use += 1

                }
                .padding(16.dp)
        )


    }

}


@Composable
fun locationA2(modifier: Modifier = Modifier) {


    val p2use by useProvider(p2)

    Column {
        Text(
            text = "A2 : $p2use",
        )
    }


}