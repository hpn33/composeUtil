package util.compose.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier


fun Modifier.disableClick(): Modifier =
    clickable(MutableInteractionSource(), null) { /* catch the click -> to don't close panel */ }


// TODO : rename -> clickableNoEffect
inline fun Modifier.clickableSilent(noinline onClick: () -> Unit): Modifier =
    clickable(MutableInteractionSource(), null, onClick = onClick)
