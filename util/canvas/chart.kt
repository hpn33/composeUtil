package util.canvas

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import util.color.Colors


inline fun DrawScope.drawCircleChartComplete(
    radius: Float,
    color: Color = Colors.white,
) {
    drawCircleChartByRatio(color = color, radius = radius)
}


inline fun DrawScope.drawCircleChartByRatio(
    offset: Float = 0f,
    ratio: Float = 1f,
    radius: Float,
    color: Color,
) {
    drawCircleChartByDeg(offset, 360f * ratio, radius, color)
}


inline fun DrawScope.drawCircleChartByDeg(
    offset: Float = 0f,
    value: Float = 360f,
    radius: Float = 1f,
    color: Color = Colors.white,
) {
    drawArc(
        color = color,
        startAngle = offset,
        sweepAngle = value,
        useCenter = true,
        topLeft = center - Offset(radius, radius),
        size = size.copy(width = radius * 2, height = radius * 2)
    )
}
