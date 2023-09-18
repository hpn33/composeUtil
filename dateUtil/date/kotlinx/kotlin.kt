package dateUtil.date.kotlinx

import kotlinx.datetime.Clock
import dateUtil.date.convert.toLocalDateX


inline fun nowInstantX() = Clock.System.now()
inline fun nowX() = nowInstantX().toLocalDateTimeX()
inline fun nowLocalDateX() = nowX().toLocalDateX()
