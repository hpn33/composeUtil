package util.compose.dialog.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import util.color.Colors
import util.compose.modifier.disableClick
import util.compose.ui.ux.size.round20d


@Composable
inline fun DialogPanel(modifier: Modifier = Modifier, content: @Composable () -> Unit) {


    Box(
        modifier
            .clip(round20d)
            .disableClick()
            .background(Colors.white, round20d)
    ) {

        content()
    }
}