package dateUtil.date.prime

import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.persian.PersianCalendar
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import dateUtil.date.kotlinx.nowX
import dateUtil.date.kotlinx.toLocalDateTimeX


// convert
// civil
inline fun LocalDateTime.toCivil() = toPrimeCalendar().toCivil()
inline fun LocalDate.toCivil() = toLocalDateTimeX().toCivil()


// persion
inline fun LocalDateTime.toPersian() = toPrimeCalendar().toPersian()
inline fun LocalDate.toPersian() = toLocalDateTimeX().toPersian()


// now
inline fun nowPersian() = nowX().toPersian()

inline fun nowCivil() = nowX().toCivil()


// make


inline fun makePersianFromKotlinX(year: String, month: String, day: String) =
    makePersianFromKotlinX(year.toInt(), month.toInt(), day.toInt())

inline fun makePersianFromKotlinX(year: Int, month: Int, day: Int) =
    PersianCalendar().apply { set(year, month - 1, day) }

/// get

data class NormalNumericDate(
    val year: Int,
    val month: Int,
    val day: Int,
)

fun PrimeCalendar.getNormalNumericDate() = NormalNumericDate(year, month + 1, dayOfMonth)
fun PersianCalendar.getNormalNumericDate() = NormalNumericDate(year, month + 1, dayOfMonth)