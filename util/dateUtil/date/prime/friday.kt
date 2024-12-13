package util.dateUtil.date.prime

import com.aminography.primecalendar.persian.PersianCalendar
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

inline fun LocalDateTime.isLastOfWeek() = toPersian().isLastOfWeek()
inline fun LocalDate.isLastOfWeek() = toPersian().isLastOfWeek()
inline fun PersianCalendar.isLastOfWeek() = dayOfWeek == 6
