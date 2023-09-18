package dateUtil.date

import kotlin.time.Duration
import kotlin.time.DurationUnit


inline val Duration.day
    get() = toDouble(DurationUnit.DAYS)

inline val Duration.intDay
    get() = day.toInt()


inline val Duration.floatDay
    get() = day.toFloat()
