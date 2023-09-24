package dateUtil.date.kotlinx

import dateUtil.date.convert.toLocalDateX
import kotlinx.datetime.Clock


inline fun nowInstantX() = Clock.System.now()
inline fun nowX() = nowInstantX().toLocalDateTimeX()
inline fun nowLocalDateX() = nowX().toLocalDateX()
