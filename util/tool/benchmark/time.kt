package util.tool.benchmark

import util.dateUtil.date.kotlinx.nowX
import util.dateUtil.date.kotlinx.toLocalTimeX

inline fun <T> timerBench(tag: String = "timer", action: () -> T): T {

    val time = nowX().toLocalTimeX().nanosecond

    val t = action()

    println("time( $tag ): ${(nowX().toLocalTimeX().nanosecond - time) / 1_000_000_000f}")

    return t
}
