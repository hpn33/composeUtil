package composeUtil.navigation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import composeUtil.navigation.nav

@Composable
fun BackButton() {
    IconButton(onClick = { nav.back() }) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back to previous view")
    }
}