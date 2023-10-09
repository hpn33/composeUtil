package util.dateUtil.date.prime

import com.aminography.primecalendar.PrimeCalendar

inline fun <T : PrimeCalendar> T.goToFirstDayOfMonth() =
    apply { set(year, month, 1) }


inline fun <T : PrimeCalendar> T.goToLastDayOfMonth() =
    apply { set(year, month, monthLength) }
