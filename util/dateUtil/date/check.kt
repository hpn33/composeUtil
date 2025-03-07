package util.dateUtil.date

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import util.dateUtil.date.kotlinx.nowLocalDateX
import util.dateUtil.date.kotlinx.nowX
import util.dateUtil.date.kotlinx.toLocalDateX
import util.dateUtil.date.prime.goToFirstOfWeek
import util.dateUtil.date.prime.goToNextDay
import util.dateUtil.date.prime.goToPrevDay
import util.dateUtil.date.prime.toLocalDateX
import util.dateUtil.date.prime.toPersian
import util.dateUtil.date.prime.toPrimeCivil


inline val LocalDateTime.isNow
    get() = this == nowX()


inline val LocalDateTime.isBeforeNow
    get() = this < nowX()


inline val LocalDateTime.isAfterNow
    get() = this > nowX()


// by day

inline val LocalDateTime.isBeforeToday
    get() = this.toLocalDateX().isBeforeToday
inline val LocalDate.isBeforeToday
    get() = this < nowLocalDateX()


inline fun LocalDate.isBeforeDate(target: LocalDate) =
    this < target


inline val LocalDateTime.isToday
    get() = this.toLocalDateX().isToday
inline val LocalDate.isToday
    get() = this == nowLocalDateX()

inline fun LocalDate.isSameDate(target: LocalDate) =
    this == target


inline val LocalDateTime.isAfterToday
    get() = this.toLocalDateX().isAfterToday
inline val LocalDate.isAfterToday
    get() = this > nowLocalDateX()

inline fun LocalDate.isAfterDate(target: LocalDate) =
    this > target


inline val LocalDateTime.isTodayOrAfter
    get() = this.toLocalDateX().isTodayOrAfter
inline val LocalDate.isTodayOrAfter
    get() = this.isToday || this.isAfterToday


inline fun LocalDate.isTodayOrAfter(target: LocalDate) =
    this.isSameDate(target) || this.isAfterDate(target)


inline val LocalDateTime.isTodayOrBefore
    get() = this.toLocalDateX().isTodayOrAfter
inline val LocalDate.isTodayOrBefore
    get() = this.isToday || this.isBeforeToday

inline fun LocalDate.isTodayOrBefore(target: LocalDate) =
    this.isSameDate(target) || this.isBeforeDate(target)

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


// ---------------------

inline val LocalDateTime.isThisWeek
    get() = this.toLocalDateX().isThisWeek
inline val LocalDate.isThisWeek: Boolean
    get() {

        return this.toPersian().goToFirstOfWeek().timeInMillis ==
                nowX().toPersian().goToFirstOfWeek().timeInMillis
    }
