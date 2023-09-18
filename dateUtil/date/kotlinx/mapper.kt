package dateUtil.date.kotlinx

import kotlinx.datetime.*
import dateUtil.date.convert.toLocalDateX


inline fun Instant.toLocalDateTimeX() = toLocalDateTime(TimeZone.currentSystemDefault())
inline fun Instant.toLocalDateX() = toLocalDateTimeX().toLocalDateX()

fun LocalDate.toLocalDateTimeX(): LocalDateTime = LocalDateTime(this.year, this.month, this.dayOfMonth, 0, 0, 0)

fun LocalDateTime.toInstant() = toInstant(TimeZone.currentSystemDefault())
fun LocalDateTime.toLocalDate(): LocalDate = LocalDate(this.year, this.month, this.dayOfMonth)


fun LocalDateTime.removeTime(): LocalDateTime = LocalDateTime(this.year, this.month, this.dayOfMonth, 0, 0, 0)

fun LocalDateTime.removeDate(): LocalDateTime = LocalDateTime(0, 0, 0, this.hour, this.minute, this.second)


//--------
