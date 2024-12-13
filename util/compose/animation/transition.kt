package util.compose.animation

import androidx.compose.animation.*

inline fun slideInTop() = slideInVertically { -it }
inline fun slideInBottom() = slideInVertically { it }

inline fun slideOutTop() = slideOutVertically { -it }
inline fun slideOutBottom() = slideOutVertically { it }

inline fun slideInRight() = slideInHorizontally { it }
inline fun slideInLeft() = slideInHorizontally { -it }

inline fun slideOutRight() = slideOutHorizontally { it }
inline fun slideOutLeft() = slideOutHorizontally { -it }