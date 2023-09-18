package dateUtil.date.convert

import kotlinx.datetime.LocalDateTime
import java.time.LocalDate


fun LocalDateTime.toLocalDateX() = kotlinx.datetime.LocalDate(year, month, dayOfMonth)
fun LocalDate.toLocalDateTimeX() = kotlinx.datetime.LocalDateTime(year, month, dayOfMonth, 0, 0, 0, 0)
