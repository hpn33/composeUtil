package util.compose.navigation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import util.compose.navigation.NavService
import util.compose.navigation.NavStackService
import util.compose.navigation.nav

@Composable
fun BackButton(navService: NavService? = null) {
    IconButton(onClick = { (navService ?: nav).back() }) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to previous view")
    }
}

@Composable
fun BackButton(navService: NavStackService) {
    IconButton(onClick = { navService.back() }) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back to previous view")
    }
}