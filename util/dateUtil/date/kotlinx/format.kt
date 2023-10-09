package util.dateUtil.date.kotlinx

import com.aminography.primecalendar.PrimeCalendar
import dateUtil.date.java.nowJ
import dateUtil.date.prime.getNormalNumericDate
import kotlinx.datetime.*
import java.time.Period


/**
 * from birthday
 */
fun LocalDateTime.toAge() =
    Period.between(
        this.toJavaLocalDateTime().toLocalDate(),
        nowJ().toLocalDate()
    ).toKotlinDatePeriod()

fun DatePeriod.toAgeFormate() =
    "${years}y ${months}m ${days}d"

fun LocalDateTime.toDateFormate() =
    "${year}-${monthNumber}-${dayOfMonth}"

fun LocalDate.toDateFormate() =
    "${year}-${monthNumber}-${dayOfMonth}"

fun LocalDateTime.toDateFormateWithHash() =
    "${year}/${monthNumber}/${dayOfMonth}"

fun LocalDate.toDateFormateWithHash() =
    "${year}/${monthNumber}/${dayOfMonth}"

// ===============


fun PrimeCalendar.toDateFormate() =
    getNormalNumericDate()
        .let { "${it.year}-${it.month}-${it.day}" }

fun PrimeCalendar.toDateFormateWithHash() =
    getNormalNumericDate()
        .let { "${it.year}/${it.month}/${it.day}" }


//    fun formatNoteDate(dateTime: LocalDateTime): String {
//        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
//        val day = if(dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
//        val year = dateTime.year
//        val hour = if(dateTime.hour < 10) "0${dateTime.hour}" else dateTime.hour
//        val minute = if(dateTime.minute < 10) "0${dateTime.minute}" else dateTime.minute
//
//        return buildString {
//            append(month)
//            append(" ")
//            append(day)
//            append(" ")
//            append(year)
//            append(", ")
//            append(hour)
//            append(":")
//            append(minute)
//        }
//    }
