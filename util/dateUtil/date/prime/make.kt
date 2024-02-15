package util.dateUtil.date.prime

import com.aminography.primecalendar.civil.CivilCalendar
import com.aminography.primecalendar.common.operators.dayOfMonth
import com.aminography.primecalendar.common.operators.minus
import com.aminography.primecalendar.common.operators.plus
import com.aminography.primecalendar.persian.PersianCalendar
import hpn.routine.ui.page.subject.detail.DayData
import kotlinx.datetime.LocalDate


fun makeWeekCenterOf(selectedDay: LocalDate) =
    buildList {

        val centerDay = selectedDay.toPrimeCivil()


        repeat(3) {
            add((centerDay.minus((3 - (it)).dayOfMonth) as CivilCalendar).toPersian())
        }

        add(centerDay.toPersian())

        repeat(3) {
            add((centerDay.plus((it + 1).dayOfMonth) as CivilCalendar).toPersian())
        }
    }


fun makeWeek(selectedDay: LocalDate) =
    buildList<PersianCalendar> {
        val firstOfWeek = selectedDay.toPersian().goToFirstOfWeek()

        repeat(7) {
            add(firstOfWeek.plus(it.dayOfMonth) as PersianCalendar)
        }
    }

fun makeMonth(selectedDay: LocalDate) =
    buildList<PersianCalendar> {
        val firstOfMonth = selectedDay.toPersian().goToFirstDayOfMonth()

        repeat(firstOfMonth.monthLength) {
            add(firstOfMonth.plus(it.dayOfMonth) as PersianCalendar)
        }
    }


fun makeMonthToWeeks(selectedDay: LocalDate): List<List<DayData>> {

    val thisMonth = selectedDay.toPersian()

    val firstDayLocate = thisMonth.goToFirstDayOfMonth().dayOfWeek

    val weeks = buildList {


        repeat(6) { weekIndex ->

            add(
                buildList {
                    repeat(7) { dayOfWeekIndex ->


                        val index = (weekIndex * 7) + (dayOfWeekIndex + 1)
                        val dayIndex = index - (firstDayLocate)

//                            println("$index($weekIndex:$dayOfWeekIndex) : $dayIndex")

                        val dayDate =
                            if (dayIndex in 1..thisMonth.monthLength)
                                thisMonth.let {
                                    PersianCalendar()
                                        .apply { set(it.year, it.month, dayIndex) }
                                }
                            else null

//                            val record = dayDate?.let { state.getDayRecord(it) }

                        add(DayData(index, /*dayIndex,*/ dayDate/*, record*/))
                    }
                }
            )
        }

    }

    return weeks
}