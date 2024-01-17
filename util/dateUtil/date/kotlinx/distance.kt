package util.dateUtil.date.kotlinx

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.until


inline infix fun LocalDate.dayUntil(end: LocalDate) =
    this.until(end, DateTimeUnit.DAY)

inline fun LocalDateTime.dayUntil(end: LocalDate) =
    this.toLocalDateX().dayUntil(end)

inline fun LocalDate.dayUntil(end: LocalDateTime) =
    this.dayUntil(end.toLocalDateX())

inline fun LocalDateTime.dayUntil(end: LocalDateTime) =
    this.toLocalDateX().dayUntil(end.toLocalDateX())


//
//
inline infix fun LocalDate.daysBetween(end: LocalDate): Int {
    val until = this.dayUntil(end)
    return until.daysBetween()
}

inline fun LocalDateTime.daysBetween(end: LocalDate): Int {
    val until = this.dayUntil(end)
    return until.daysBetween()
}

inline fun LocalDate.daysBetween(end: LocalDateTime): Int {
    val until = this.dayUntil(end)
    return until.daysBetween()
}

inline fun LocalDateTime.daysBetween(end: LocalDateTime): Int {
    val until = this.dayUntil(end)
    return until.daysBetween()
}


inline fun Int.daysBetween() =
    if (this == 0) 0 else (this - 1)