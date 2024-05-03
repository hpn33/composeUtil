package util.dateUtil.date.prime

import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.common.operators.dayOfMonth
import com.aminography.primecalendar.common.operators.minus
import com.aminography.primecalendar.common.operators.month
import com.aminography.primecalendar.common.operators.plus
import com.aminography.primecalendar.persian.PersianCalendar


inline fun <T : PrimeCalendar> T.goForwardAsWeek(weeks: Int = 1) =
    plus((weeks * 7).dayOfMonth) as T

inline fun <T : PrimeCalendar> T.goBackwardAsWeek(weeks: Int = 1) =
    minus((weeks * 7).dayOfMonth) as T


fun <T : PrimeCalendar> T.nextWeek() = goForwardAsWeek()
fun <T : PrimeCalendar> T.prevWeek() = goBackwardAsWeek()
// ---


inline fun <T : PrimeCalendar> T.goToNextDay(days: Int = 1) =
    plus(days.dayOfMonth) as T

inline fun <T : PrimeCalendar> T.goToPrevDay(days: Int = 1) =
    minus(days.dayOfMonth) as T


fun <T : PrimeCalendar> T.nextDay() = goToNextDay()
fun <T : PrimeCalendar> T.prevDay() = goToPrevDay()

// ---


inline fun <T : PrimeCalendar> T.goForwardAsMonth(months: Int = 1) =
    plus(months.month) as T

inline fun <T : PrimeCalendar> T.goBackwardAsMonth(months: Int = 1) =
    minus(months.month) as T


fun <T : PrimeCalendar> T.nextMonth() = goForwardAsMonth()
fun <T : PrimeCalendar> T.prevMonth() = goBackwardAsMonth()


// ---


fun getFirstOfWeek(selectedDay: PrimeCalendar): PrimeCalendar {


//    val now = selectedDay.toPersian()
    val firstDayOfWeek = selectedDay.firstDayOfWeek

    var firstOfWeek = selectedDay


    // get first day of week
    while (true) {

        if (firstOfWeek.dayOfWeek == firstDayOfWeek) {
            break
        }

        firstOfWeek = firstOfWeek.plus((-1).dayOfMonth) as PersianCalendar
    }


    return firstOfWeek

}

inline fun <T : PrimeCalendar> T.goToFirstDayOfMonth() =
    apply { set(year, month, 1) }


inline fun <T : PrimeCalendar> T.goToLastDayOfMonth() =
    apply { set(year, month, monthLength) }


// ---


inline fun <T : PrimeCalendar> T.goToFirstOfWeek() =
    getFirstOfWeek(this as PrimeCalendar) as T


inline fun <T : PrimeCalendar> T.goToEndOfWeek() =
    getFirstOfWeek(this as PrimeCalendar)
        .plus(7.dayOfMonth) as T