package util.service.signal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import util.compose.state.useState


val keyA = SignalKey<Unit>()
val keyB = SignalKey<Int>()

@Composable
fun testPortal() {

    SignalScope {

        Column {

            val signalService = useSignalService()



            Box {
                var a by useState(0)

                signalService.receiver(keyA) {
                    a++
                }

                Text("a-$a",
                    modifier = Modifier.clickable { signalService.signal(keyB, a) }
                )
            }



            Box {

                var b by useState(0)

                signalService.receiver(keyB) {
                    b += it
                }

                Text("b-$b",
                    modifier = Modifier.clickable { signalService.signal(keyA) }

                )
            }


            Box {
                var a by useState(0)

                signalService.receiver(keyA) {
                    a++
                }

                Text(
                    "a2-$a",
                )
            }

        }


    }


}