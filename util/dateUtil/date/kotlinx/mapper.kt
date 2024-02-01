package util.dateUtil.date.kotlinx

import kotlinx.datetime.*
import util.dateUtil.date.convert.toLocalDateX

// instant -> ?
inline fun Instant.toLocalDateTimeX() = toLocalDateTime(TimeZone.currentSystemDefault())
inline fun Instant.toLocalDateX() = toLocalDateTimeX().toLocalDateX()
inline fun Instant.toLocalTimeX() = toLocalDateTimeX().toLocalTimeX()


// localDateTime -> ?
fun LocalDateTime.toInstantX() = toInstant(TimeZone.currentSystemDefault())
fun LocalDateTime.toLocalDateX(): LocalDate = LocalDate(this.year, this.month, this.dayOfMonth)
fun LocalDateTime.toLocalTimeX(): LocalTime = LocalTime(hour, minute, second, nanosecond)


// localDate -> ?
fun LocalDate.toInstantX() = toLocalDateTimeX().toInstantX()
fun LocalDate.toLocalDateTimeX(): LocalDateTime = LocalDateTime(this.year, this.month, this.dayOfMonth, 0, 0, 0)


// clear
fun LocalDateTime.removeTime(): LocalDateTime = LocalDateTime(this.year, this.month, this.dayOfMonth, 0, 0, 0)
fun LocalDateTime.removeDate(): LocalDateTime = LocalDateTime(0, 0, 0, this.hour, this.minute, this.second)


