package util.dateUtil.date.prime

import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primecalendar.persian.PersianCalendar
import util.dateUtil.date.kotlinx.toInstantX
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import util.dateUtil.date.kotlinx.toLocalDateTimeX
import kotlinx.datetime.LocalDate
import java.util.*



// native -> prime civil

inline fun Instant.toPrimeCivil() =
    toLocalDateTimeX().toPrimeCivil()

fun LocalDateTime.toPrimeCivil() =
    CivilCalendar(locale = Locale.ENGLISH)
        .also { it.set(year, (monthNumber - 1), dayOfMonth) }

inline fun LocalDate.toPrimeCivil() =
    toLocalDateTimeX().toPrimeCivil()


// prime -> native

inline fun PrimeCalendar.toInstance() =
    toCivil().toInstantX()

inline fun PrimeCalendar.toLocalDateTimeX() =
    toCivil().toLocalDateTimeX()

inline fun PrimeCalendar.toLocalDateX() =
    toCivil().toLocalDateX()


inline fun CivilCalendar.toInstantX() =
    toLocalDateTimeX().toInstantX()

fun CivilCalendar.toLocalDateTimeX() =
    LocalDateTime(year, month + 1, dayOfMonth, 0, 0)

fun CivilCalendar.toLocalDateX() =
    LocalDate(year, month + 1, dayOfMonth)


// persian -> ?


inline fun PersianCalendar.toInstantX() =
    toCivil().toInstantX()

inline fun PersianCalendar.toLocalDateTimeX() =
    toCivil().toLocalDateTimeX()

inline fun PersianCalendar.toLocalDateX() =
    toCivil().toLocalDateX()


