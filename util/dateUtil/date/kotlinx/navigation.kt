package util.dateUtil.date.kotlinx

import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.common.operators.dayOfMonth
import com.aminography.primecalendar.common.operators.minus
import com.aminography.primecalendar.common.operators.month
import com.aminography.primecalendar.common.operators.plus
import kotlinx.datetime.LocalDate
import util.dateUtil.date.prime.*


inline fun LocalDate.goForwardAsWeek(weeks: Int = 1) =
    toPersian().goForwardAsWeek(weeks).toLocalDateX()

inline fun LocalDate.goBackwardAsWeek(weeks: Int = 1) =
    toPersian().goBackwardAsWeek(weeks).toLocalDateX()

fun LocalDate.nextWeek() = goForwardAsWeek()
fun LocalDate.prevWeek() = goBackwardAsWeek()
// ---


inline fun LocalDate.goToNextDay(days: Int = 1) =
    toPersian().goToNextDay(days).toLocalDateX()

inline fun LocalDate.goToPrevDay(days: Int = 1) =
    toPersian().goToPrevDay(days).toLocalDateX()


fun LocalDate.nextDay() = goToNextDay()
fun LocalDate.prevDay() = goToPrevDay()

// ---


inline fun LocalDate.goForwardAsMonth(months: Int = 1) =
    toPersian().goForwardAsMonth(months).toLocalDateX()

inline fun LocalDate.goBackwardAsMonth(months: Int = 1) =
    toPersian().goBackwardAsMonth(months).toLocalDateX()


fun LocalDate.nextMonth() = goForwardAsMonth()
fun LocalDate.prevMonth() = goBackwardAsMonth()

