package util.dateUtil.date

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import util.dateUtil.date.kotlinx.nowLocalDateX
import util.dateUtil.date.kotlinx.nowX
import util.dateUtil.date.kotlinx.toLocalDateX
import util.dateUtil.date.prime.goToNextDay
import util.dateUtil.date.prime.goToPrevDay
import util.dateUtil.date.prime.toLocalDateX
import util.dateUtil.date.prime.toPrimeCivil


inline val LocalDateTime.isNow
    get() = this == nowX()


inline val LocalDateTime.isBeforeNow
    get() = this < nowX()


inline val LocalDateTime.isAfterNow
    get() = this > nowX()


// by day

inline val LocalDateTime.isToday
    get() = this.toLocalDateX() == nowLocalDateX()


inline val LocalDateTime.isYesterday
    get() = this.toLocalDateX() == nowLocalDateX().toPrimeCivil().goToPrevDay().toLocalDateX()

inline fun LocalDateTime.isYesterdayOf(date: LocalDate) =
    this.toLocalDateX() == date.toPrimeCivil().goToPrevDay().toLocalDateX()


inline val LocalDateTime.isTomorrow
    get() = this.toLocalDateX() == nowLocalDateX().toPrimeCivil().goToNextDay().toLocalDateX()

inline fun LocalDateTime.isTomorrowOf(date: LocalDate) =
    this.toLocalDateX() == date.toPrimeCivil().goToNextDay().toLocalDateX()


inline val LocalDateTime.isBeforeToday
    get() = this.toLocalDateX() < nowLocalDateX()


inline val LocalDateTime.isAfterToday
    get() = this.toLocalDateX() > nowLocalDateX()



