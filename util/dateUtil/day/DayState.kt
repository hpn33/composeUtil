package util.dateUtil.day

import kotlinx.datetime.LocalDate
import util.dateUtil.date.isToday
import util.dateUtil.date.prime.isLastOfWeek


fun dayStateWithWeekEnd(
    targetDate: LocalDate,
    selectedDate: LocalDate? = null,
): DayStatus {

    val isToday = targetDate.isToday
    val isLastOfWeek = targetDate.isLastOfWeek()

    val isSelected = targetDate == selectedDate

    return when {
        isLastOfWeek && isToday && isSelected -> DayStatus.FridayAndTodayAndSelected
        isLastOfWeek && isToday -> DayStatus.FridayAndToday
        isLastOfWeek && isSelected -> DayStatus.FridayAndSelected
        isLastOfWeek -> DayStatus.Friday

        isToday && isSelected -> DayStatus.TodayAndSelected
        isSelected -> DayStatus.Selected
        isToday -> DayStatus.Today
        else -> DayStatus.None
    }

}

fun dayState(
    targetDate: LocalDate,
    selectedDate: LocalDate? = null,
): DayStatus {

    val isToday = targetDate.isToday

    val isSelected = targetDate == selectedDate

    return when {
        isToday && isSelected -> DayStatus.TodayAndSelected
        isSelected -> DayStatus.Selected
        isToday -> DayStatus.Today
        else -> DayStatus.None
    }

}


enum class DayStatus {
    None,
    Today,
    TodayAndSelected,
    Selected,
    Friday,
    FridayAndToday,
    FridayAndSelected,
    FridayAndTodayAndSelected
}