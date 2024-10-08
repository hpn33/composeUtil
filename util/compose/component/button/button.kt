package util.compose.component.button

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import util.compose.ui.ux.size.round100p


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null,
    borderColor: Color = Color.Transparent,
    borderWidth: Dp = 0.dp,
    backColor: Color = Color.Unspecified,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {

    var rootModifier: Modifier = Modifier

    rootModifier =
        if (enabled)
            if (onClick != null && onLongClick != null) {
                rootModifier.then(Modifier.combinedClickable(onClick = onClick, onLongClick = onLongClick))
            } else if (onClick != null) {
                rootModifier.then(Modifier.clickable(onClick = onClick))
            } else {
                rootModifier
            }
        else
            rootModifier

    Box(
        Modifier
            .clip(round100p)
            .then(rootModifier)
            .border(borderWidth, borderColor, round100p)
            .background(backColor, round100p)
            .padding(5.dp)
    ) {

        content()

    }

}
