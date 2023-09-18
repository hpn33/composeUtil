package dateUtil.date.prime

import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primecalendar.persian.PersianCalendar
import dateUtil.date.convert.toLocalDateTimeX
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import dateUtil.date.kotlinx.toLocalDateTimeX
import java.time.LocalDate
import java.util.*


inline fun LocalDate.toPrimeCalendar() =
    toLocalDateTimeX().toPrimeCalendar()


inline fun LocalDateTime.toPrimeCalendar() =
    CivilCalendar(locale = Locale.ENGLISH)
        .also { it.set(year, (monthNumber - 1), dayOfMonth) }

fun CivilCalendar.toLocalDateTimeX() =
    LocalDateTime(year, month + 1, dayOfMonth, 0, 0)

fun PersianCalendar.toLocalDateTimeX() =
    toCivil().toLocalDateTimeX()

fun PersianCalendar.toLocalDateX() =
    toCivil()
        .let {
            kotlinx.datetime.LocalDate(it.year, it.month + 1, it.dayOfMonth)
        }


inline fun Instant.toPrimeCalendar() =
    toLocalDateTimeX().toPrimeCalendar()