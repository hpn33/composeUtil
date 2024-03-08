package util.dateUtil.date.kotlinx

import util.dateUtil.date.convert.toLocalDateX
import kotlinx.datetime.Clock
import util.dateUtil.date.prime.*


inline fun nowInstantX() = Clock.System.now()
inline fun nowX() = nowInstantX().toLocalDateTimeX()
inline fun nowLocalDateX() = nowX().toLocalDateX()


inline fun tomorrowX() = nowX().toPrimeCivil().goToNextDay().toLocalDateX()
inline fun yesterdayX() = nowX().toPrimeCivil().goToPrevDay().toLocalDateX()