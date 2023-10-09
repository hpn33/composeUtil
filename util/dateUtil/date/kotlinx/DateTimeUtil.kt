package util.dateUtil.date.kotlinx

import kotlinx.datetime.*



fun LocalDateTime.toEpochMillis(): Long {
    return toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

fun Long.toLocalDateTimeLong() =
    Instant
        .fromEpochMilliseconds(this)
        .toLocalDateTime(TimeZone.currentSystemDefault())
