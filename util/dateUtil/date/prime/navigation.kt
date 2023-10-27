package util.dateUtil.date.prime

import com.aminography.primecalendar.PrimeCalendar
import com.aminography.primecalendar.common.operators.dayOfMonth
import com.aminography.primecalendar.common.operators.plus
import com.aminography.primecalendar.persian.PersianCalendar
import kotlin.time.Duration.Companion.days

inline fun <T : PrimeCalendar> T.goToFirstDayOfMonth() =
    apply { set(year, month, 1) }


inline fun <T : PrimeCalendar> T.goToLastDayOfMonth() =
    apply { set(year, month, monthLength) }


inline fun <T : PrimeCalendar> T.goToFirstOfWeek() =
    getFirstOfWeek(this as PrimeCalendar) as T


inline fun <T : PrimeCalendar> T.goToEndOfWeek() =
    getFirstOfWeek(this as PrimeCalendar)
        .plus(7.dayOfMonth) as T


inline fun <T : PrimeCalendar> T.goToPrevDay() =
    plus((-1).dayOfMonth) as T


inline fun <T : PrimeCalendar> T.goToNextDay() =
    plus(1.dayOfMonth) as T





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
