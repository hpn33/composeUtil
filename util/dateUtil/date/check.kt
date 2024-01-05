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
    get() = this.toLocalDateX().isToday
inline val LocalDate.isToday
    get() = this == nowLocalDateX()


inline val LocalDateTime.isYesterday
    get() = this.toLocalDateX().isYesterday
inline val LocalDate.isYesterday
    get() = this == nowLocalDateX().toPrimeCivil().goToPrevDay().toLocalDateX()


inline fun LocalDateTime.isYesterdayOf(date: LocalDateTime) =
    this.toLocalDateX().isYesterdayOf(date.toLocalDateX())

inline fun LocalDateTime.isYesterdayOf(date: LocalDate) =
    this.toLocalDateX().isYesterdayOf(date)

inline fun LocalDate.isYesterdayOf(date: LocalDateTime) =
    this.isYesterdayOf(date.toLocalDateX())

inline fun LocalDate.isYesterdayOf(date: LocalDate) =
    this == date.toPrimeCivil().goToPrevDay().toLocalDateX()


inline val LocalDateTime.isTomorrow
    get() = this.toLocalDateX().isTomorrow
inline val LocalDate.isTomorrow
    get() = this == nowLocalDateX().toPrimeCivil().goToNextDay().toLocalDateX()


inline fun LocalDateTime.isTomorrowOf(date: LocalDateTime) =
    this.toLocalDateX().isTomorrowOf(date.toLocalDateX())

inline fun LocalDateTime.isTomorrowOf(date: LocalDate) =
    this.toLocalDateX().isTomorrowOf(date)

inline fun LocalDate.isTomorrowOf(date: LocalDateTime) =
    this.isTomorrowOf(date.toLocalDateX())

inline fun LocalDate.isTomorrowOf(date: LocalDate) =
    this == date.toPrimeCivil().goToNextDay().toLocalDateX()


inline val LocalDateTime.isBeforeToday
    get() = this.toLocalDateX().isBeforeToday
inline val LocalDate.isBeforeToday
    get() = this < nowLocalDateX()


inline val LocalDateTime.isAfterToday
    get() = this.toLocalDateX().isAfterToday
inline val LocalDate.isAfterToday
    get() = this > nowLocalDateX()



