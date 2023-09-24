package dateUtil.date

import com.aminography.primecalendar.persian.PersianCalendar
import kotlinx.datetime.Instant
import dateUtil.date.kotlinx.nowInstantX
import dateUtil.date.prime.toPrimeCivil
import kotlin.time.Duration.Companion.days

// java.date -> kotlinx
// kotlinx -> prime
// java.date -> prime


fun makeDay(now: Instant = nowInstantX(), plus: Int = 0): PersianCalendar {

    var dateDay = now

    if (plus != 0) {
        dateDay = now.plus(plus.days)
    }

    return dateDay.toPrimeCivil().toPersian()


}

fun makeDayMinus(now: Instant = nowInstantX(), minus: Int = 0): PersianCalendar {

    var dateDay = now

    if (minus != 0) {
        dateDay = now.minus(minus.days)
    }

    return dateDay.toPrimeCivil().toPersian()


}


