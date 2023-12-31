//package composeUtil.animation
//
//import androidx.compose.animation.core.*
//import androidx.compose.foundation.layout.Box
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.graphicsLayer
//
///**
// * [CrossSlideHorizontal] allows to switch between two layouts with a CrossSlide animation.
// *
// *
// *
// * @param targetState is a key representing your target layout state. Every time you change a key
// * the animation will be triggered. The [content] called with the old key will be faded out while
// * the [content] called with the new key will be faded in.
// * @param modifier Modifier to be applied to the animation container.
// * @param animationSpec the [AnimationSpec] to configure the animation.
// * @param reverseAnimation to reverse the sliding, for example when pressing the back button
// */
//
//@Composable
//fun <T> CrossSlideList(
//    currentState: T,
//    targetState: T,
//    orderedContent: List<T> = emptyList<T>(),
//    modifier: Modifier = Modifier,
//    animationSpec: FiniteAnimationSpec<Offset> = tween(500),
//    content: @Composable (T) -> Unit
//) {
//    val direction: Int = if (orderedContent.indexOf(currentState) < orderedContent.indexOf(targetState)) 1 else -1
//
//    val items = remember { mutableStateListOf<SlideInOutAnimationState<T>>() }
//    val transitionState = remember { MutableTransitionState(targetState) }
//
//    val targetChanged = (targetState != transitionState.targetState)
//    transitionState.targetState = targetState
//    val transition: Transition<T> = updateTransition(transitionState)
//
//    if (targetChanged || items.isEmpty()) {
//        // Only manipulate the list when the state is changed, or in the first run.
//        val keys = items.map { it.key }.run {
//            if (!contains(targetState)) {
//                toMutableList().also { it.add(targetState) }
//            } else {
//                this
//            }
//        }
//        items.clear()
//        keys.mapTo(items) { key ->
//
//            SlideInOutAnimationState(key) {
//                val xTransition by transition.animateOffset(
//                    transitionSpec = { animationSpec },
//                    label = ""
//                ) {
//                    if (it == key) Offset(0f, 0f) else Offset(1000f, 1000f)
//                }
//
//                Box(
//                    modifier.graphicsLayer {
//                        this.translationX =
//                            if (transition.targetState == key) direction * xTransition.x else direction * -xTransition.x
//                    }
//                ) {
//
//                    content(key)
//                }
//            }
//        }
//    } else if (transitionState.currentState == transitionState.targetState) {
//        // Remove all the intermediate items from the list once the animation is finished.
//        items.removeAll { it.key != transitionState.targetState }
//    }
//
//    Box(modifier) {
//        items.forEach {
//            key(it.key) {
//                it.content()
//            }
//        }
//    }
//}
//
//
