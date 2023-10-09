package util.composeUtil.hero
//
//import androidx.compose.animation.core.Transition
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.size
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.layout.Layout
//import androidx.compose.ui.unit.dp
//import kotlin.properties.Delegates
//
//enum class SharedElementType { FROM, TO }
//
//@Composable
//fun SharedElement(
//    tag: Any,
//    type: SharedElementType,
//    modifier: Modifier = Modifier,
//    placeholder: @Composable (() -> Unit)? = null,
//    children: @Composable() () -> Unit
//) {
//    val elementInfo = SharedElementInfo(tag, type)
//    val rootState = SharedElementsRootStateAmbient.current
//
//    rootState.onElementRegistered(elementInfo)
//
//    DisposableEffect(Unit) {
//
//
//        onDispose {
//            rootState.onElementDisposed(elementInfo)
//
//        }
//    }
//
//
////    Layout() {
////
////    }
//
//    val visibilityModifier =
//        if (rootState.shouldHideElement(elementInfo)) Modifier.graphicsLayer(alpha = 0f) else Modifier
//
//
//
//    Box(
//        modifier = modifier
//            .onChildPositioned { coordinates ->
//                rootState.onElementPositioned(
//                    elementInfo = elementInfo,
//                    placeholder = placeholder ?: children,
//                    coordinates = coordinates,
//                    invalidateElement = recompose
//                )
//            }.plus(visibilityModifier)
//    ) { children() }
//
//}
//
//
//private val SharedElementsRootStateAmbient =
//    compositionLocalOf<SharedElementsRootState> {
//        error("SharedElementsRoot not found. SharedElement must be hosted in SharedElementsRoot.")
//    }
//
//private class SharedElementsRootState {
//    private val choreographer = ChoreographerWrapper()
//    val trackers = mutableMapOf<SharedElementTag, SharedElementsTracker>()
//    var invalidateTransitionsOverlay: () -> Unit = {}
//    var rootCoordinates: LayoutCoordinates? = null
//
//    fun shouldHideElement(elementInfo: SharedElementInfo): Boolean {
//        return getTracker(elementInfo).shouldHideElement
//    }
//
//    fun onElementRegistered(elementInfo: SharedElementInfo) {
//        choreographer.removeCallback(elementInfo)
//        getTracker(elementInfo).onElementRegistered(elementInfo)
//    }
//
//    fun onElementPositioned(
//        elementInfo: SharedElementInfo,
//        placeholder: @Composable() () -> Unit,
////        coordinates: LayoutCoordinates,
//        invalidateElement: () -> Unit
//    ) {
//        val element = PositionedSharedElement(
//            info = elementInfo,
//            placeholder = placeholder,
//            bounds = calculateElementBoundsInRoot(coordinates)
//        )
//        getTracker(elementInfo).onElementPositioned(element, invalidateElement)
//    }
//
//    fun onElementDisposed(elementInfo: SharedElementInfo) {
//        choreographer.postCallback(elementInfo) {
//            val tracker = getTracker(elementInfo)
//            tracker.onElementUnregistered(elementInfo)
//            if (tracker.isEmpty) trackers.remove(elementInfo.tag)
//        }
//    }
//
//    fun onDisposed() {
//        choreographer.clear()
//    }
//
//    private fun getTracker(elementInfo: SharedElementInfo): SharedElementsTracker {
//        return trackers.getOrPut(elementInfo.tag) {
//            SharedElementsTracker(onTransitionChanged = { invalidateTransitionsOverlay() })
//        }
//    }
//
//    private fun calculateElementBoundsInRoot(elementCoordinates: LayoutCoordinates): PxBounds {
//        return rootCoordinates?.childBoundingBox(elementCoordinates) ?: elementCoordinates.boundsInRoot
//    }
//}
//
//private class SharedElementsTracker(
//    private val onTransitionChanged: () -> Unit
//) {
//    private var state: State = State.Empty
//
//    var transition by Delegates.observable<SharedElementTransition?>(null) { _, oldValue, newValue ->
//        if (oldValue != newValue) {
//            (oldValue as? SharedElementTransition.InProgress)?.cleanup()
//            onTransitionChanged()
//        }
//    }
//
//    val isEmpty: Boolean get() = state is State.Empty
//
//    val shouldHideElement: Boolean get() = transition != null
//
//    fun onElementRegistered(elementInfo: SharedElementInfo) {
//        when (val state = state) {
//            is State.StartElementPositioned -> {
//                if (!state.isRegistered(elementInfo)) {
//                    this.state =
//                        State.EndElementRegistered(startElement = state.startElement, endElementInfo = elementInfo)
//                    transition = SharedElementTransition.WaitingForEndElementPosition(state.startElement)
//                }
//            }
//
//            is State.StartElementRegistered -> {
//                if (elementInfo != state.startElementInfo) {
//                    this.state = State.StartElementRegistered(startElementInfo = elementInfo)
//                }
//            }
//
//            is State.Empty -> {
//                this.state = State.StartElementRegistered(startElementInfo = elementInfo)
//            }
//        }
//    }
//
//    fun onElementPositioned(element: PositionedSharedElement, invalidateElement: () -> Unit) {
//        when (val state = state) {
//            is State.EndElementRegistered -> {
//                if (element.info == state.endElementInfo) {
//                    this.state = State.StartElementPositioned(startElement = element)
//                    transition =
//                        SharedElementTransition.InProgress(
//                            startElement = state.startElement,
//                            endElement = element,
//                            onTransitionFinished = {
//                                transition = null
//                                invalidateElement()
//                            })
//                } else if (element.info == state.startElementInfo) {
//                    this.state =
//                        State.EndElementRegistered(startElement = element, endElementInfo = state.endElementInfo)
//                    transition = SharedElementTransition.WaitingForEndElementPosition(startElement = element)
//                }
//            }
//
//            is State.StartElementRegistered -> {
//                if (element.info == state.startElementInfo) {
//                    this.state = State.StartElementPositioned(startElement = element)
//                }
//            }
//
//            State.Empty -> TODO()
//        }
//    }
//
//    fun onElementUnregistered(elementInfo: SharedElementInfo) {
//        when (val state = state) {
//            is State.EndElementRegistered -> {
//                if (elementInfo == state.endElementInfo) {
//                    this.state = State.StartElementPositioned(startElement = state.startElement)
//                    transition = null
//                } else if (elementInfo == state.startElement.info) {
//                    this.state = State.StartElementRegistered(startElementInfo = state.endElementInfo)
//                    transition = null
//                }
//            }
//
//            is State.StartElementRegistered -> {
//                if (elementInfo == state.startElementInfo) {
//                    this.state = State.Empty
//                    transition = null
//                }
//            }
//
//            State.Empty -> TODO()
//        }
//    }
//
//    private sealed class State {
//        object Empty : State()
//
//        open class StartElementRegistered(val startElementInfo: SharedElementInfo) : State() {
//            open fun isRegistered(elementInfo: SharedElementInfo): Boolean {
//                return elementInfo == startElementInfo
//            }
//        }
//
//        open class StartElementPositioned(open val startElement: PositionedSharedElement) :
//            StartElementRegistered(startElement.info)
//
//        class EndElementRegistered(
//            override val startElement: PositionedSharedElement,
//            val endElementInfo: SharedElementInfo
//        ) : StartElementPositioned(startElement) {
//            override fun isRegistered(elementInfo: SharedElementInfo): Boolean {
//                return super.isRegistered(elementInfo) || elementInfo == endElementInfo
//            }
//        }
//    }
//}
//
//private typealias SharedElementTag = Any
//
//private data class SharedElementInfo(val tag: SharedElementTag, val type: SharedElementType)
//
//private class PositionedSharedElement(
//    val info: SharedElementInfo,
//    val placeholder: @Composable() () -> Unit,
//    val bounds: PxBounds
//)
//
//
//private sealed class SharedElementTransition(val startElement: PositionedSharedElement) {
//
//    class WaitingForEndElementPosition(startElement: PositionedSharedElement) : SharedElementTransition(startElement)
//
//    class InProgress(
//        startElement: PositionedSharedElement,
//        val endElement: PositionedSharedElement,
//        var onTransitionFinished: () -> Unit
//    ) : SharedElementTransition(startElement) {
//
//        val startElementPropKeys = SharedElementPropKeys()
//        val endElementPropKeys = SharedElementPropKeys()
//
//        val transitionDefinition =
//            transitionDefinition {
//                state(State.START) {
//                    this[startElementPropKeys.position] = PxPosition(startElement.bounds.left, startElement.bounds.top)
//                    this[startElementPropKeys.scaleX] = 1f
//                    this[startElementPropKeys.scaleY] = 1f
//                    this[startElementPropKeys.alpha] = 1f
//                    this[endElementPropKeys.position] = PxPosition(
//                        x = startElement.bounds.left + (startElement.bounds.width - endElement.bounds.width) / 2f,
//                        y = startElement.bounds.top + (startElement.bounds.height - endElement.bounds.height) / 2f
//                    )
//                    this[endElementPropKeys.scaleX] = startElement.bounds.width / endElement.bounds.width
//                    this[endElementPropKeys.scaleY] = startElement.bounds.height / endElement.bounds.height
//                    this[endElementPropKeys.alpha] = 0f
//                }
//                state(State.END) {
//                    this[startElementPropKeys.position] = PxPosition(
//                        x = endElement.bounds.left + (endElement.bounds.width - startElement.bounds.width) / 2f,
//                        y = endElement.bounds.top + (endElement.bounds.height - startElement.bounds.height) / 2f
//                    )
//                    this[startElementPropKeys.scaleX] = endElement.bounds.width / startElement.bounds.width
//                    this[startElementPropKeys.scaleY] = endElement.bounds.height / startElement.bounds.height
//                    this[startElementPropKeys.alpha] = 0f
//                    this[endElementPropKeys.position] = PxPosition(endElement.bounds.left, endElement.bounds.top)
//                    this[endElementPropKeys.scaleX] = 1f
//                    this[endElementPropKeys.scaleY] = 1f
//                    this[endElementPropKeys.alpha] = 1f
//                }
//                transition(fromState = State.START, toState = State.END) {
//                    sequenceOf(startElementPropKeys, endElementPropKeys).flatMap {
//                        sequenceOf(it.position, it.scaleX, it.scaleY, it.alpha)
//                    }.forEach { key ->
//                        key using tween {
//                            duration = 1000
//                        }
//                    }
//                }
//            }
//
//        fun cleanup() {
//            onTransitionFinished = {}
//        }
//
//        enum class State {
//            START, END
//        }
//
//        class SharedElementPropKeys {
//            val position = PxPositionPropKey()
//            val scaleX = FloatPropKey()
//            val scaleY = FloatPropKey()
//            val alpha = FloatPropKey()
//        }
//    }
//}
//
//private class ChoreographerWrapper {
//    private val callbacks = mutableMapOf<SharedElementInfo, Choreographer.FrameCallback>()
//    private val choreographer = Choreographer.getInstance()
//
//    fun postCallback(elementInfo: SharedElementInfo, callback: () -> Unit) {
//        if (callbacks.containsKey(elementInfo)) return
//
//        val frameCallback = Choreographer.FrameCallback {
//            callbacks.remove(elementInfo)
//            callback()
//        }
//        callbacks[elementInfo] = frameCallback
//        choreographer.postFrameCallback(frameCallback)
//    }
//
//    fun removeCallback(elementInfo: SharedElementInfo) {
//        callbacks.remove(elementInfo)?.also(choreographer::removeFrameCallback)
//    }
//
//    fun clear() {
//        callbacks.values.forEach(choreographer::removeFrameCallback)
//        callbacks.clear()
//    }
//}
//
//
//@Composable
//fun SharedElementsRoot(children: @Composable() () -> Unit) {
//    val rootState = remember { SharedElementsRootState() }
//
//    DisposableEffect(Unit) {
//
//        onDispose {
//            rootState.onDisposed()
//        }
//    }
//
//
//    Box(modifier = Modifier
//        .onPositioned { layoutCoordinates ->
//            rootState.rootCoordinates = layoutCoordinates
//        }
//    ) {
//
//        CompositionLocalProvider(SharedElementsRootStateAmbient provides rootState) {
//            children()
//        }
//
//        SharedElementTransitionsOverlay(rootState)
//    }
//
//}
//
//
//@Composable
//private fun SharedElementTransitionsOverlay(rootState: SharedElementsRootState) {
//    rootState.invalidateTransitionsOverlay = invalidate
//    rootState.trackers.values.forEach { tracker ->
//
//        when (val transition = tracker.transition) {
//            is SharedElementTransition.WaitingForEndElementPosition -> SharedElementTransitionPlaceholder(
//                sharedElement = transition.startElement,
//                offsetX = transition.startElement.bounds.left,
//                offsetY = transition.startElement.bounds.top
//            )
//
//            is SharedElementTransition.InProgress -> Transition(
//                definition = transition.transitionDefinition,
//                initState = SharedElementTransition.InProgress.State.START,
//                toState = SharedElementTransition.InProgress.State.END,
//                onStateChangeFinished = { state ->
//                    if (state == SharedElementTransition.InProgress.State.END) {
//                        transition.onTransitionFinished()
//                    }
//                }
//            ) { transitionState ->
//                SharedElementTransitionPlaceholder(
//                    sharedElement = transition.startElement,
//                    transitionState = transitionState,
//                    propKeys = transition.startElementPropKeys
//                )
//                SharedElementTransitionPlaceholder(
//                    sharedElement = transition.endElement,
//                    transitionState = transitionState,
//                    propKeys = transition.endElementPropKeys
//                )
//            }
//
//            null -> TODO()
//        }
//    }
//}
//
//@Composable
//private fun SharedElementTransitionPlaceholder(
//    sharedElement: PositionedSharedElement,
//    transitionState: TransitionState,
//    propKeys: SharedElementTransition.InProgress.SharedElementPropKeys
//) {
//    SharedElementTransitionPlaceholder(
//        sharedElement = sharedElement,
//        offsetX = transitionState[propKeys.position].x,
//        offsetY = transitionState[propKeys.position].y,
//        scaleX = transitionState[propKeys.scaleX],
//        scaleY = transitionState[propKeys.scaleY],
//        alpha = transitionState[propKeys.alpha]
//    )
//}
//
//@Composable
//private fun SharedElementTransitionPlaceholder(
//    sharedElement: PositionedSharedElement,
//    offsetX: Float,
//    offsetY: Float,
//    scaleX: Float = 1f,
//    scaleY: Float = 1f,
//    alpha: Float = 1f
//) {
//
//    Box(
//        modifier = Modifier
//            .size(
//                width = sharedElement.bounds.width.toDp(),
//                height = sharedElement.bounds.height.toDp()
//            )
//            .offset(
//                x = offsetX.dp,
//                y = offsetY.dp
//            )
//            .graphicsLayer(
////                elevation = Float.MAX_VALUE, // TODO: re-enable in future? Depending on https://issuetracker.google.com/issues/153173354
//                scaleX = scaleX,
//                scaleY = scaleY,
//                alpha = alpha
//            ),
//    ) {
//        sharedElement.placeholder()
//    }
//}