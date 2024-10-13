package util.compose.component.date

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.aminography.primecalendar.persian.PersianCalendar
import util.compose.component.base.Filler
import util.compose.component.base.Weight
import util.compose.state.useState
import util.dateUtil.date.prime.nowPersian


@Composable
fun rememberDateFieldController(
    key: Any? = null,
    initDate: PersianCalendar = nowPersian(),
): DateFieldController {

    val controller = remember(key) { DateFieldController(initDate) }

    remember(key) { controller.dateState }
    remember(key) { controller.isDateTrue }

    return controller
}

class DateFieldController(
    initDate: PersianCalendar = nowPersian(),
) {

    val dateState = mutableStateOf(initDate)
    val isDateTrue = mutableStateOf(false)

}


@Composable
fun DateEditField( controller: DateFieldController, key: Any? = null,) {


    var dateState by remember(key) { controller.dateState }
    var isDateValid by remember(key) { controller.isDateTrue }

    println("-----${dateState.shortDateString}")

    Column {

//                var di by useState(false)
        var year by useState(dateState, dateState.year.toString())
        var month by useState(dateState, (dateState.month + 1).toString())
        var day by useState(dateState, dateState.dayOfMonth.toString())


        val yearIsValid = yearValidation(year)
        val monthIsValid = monthValidation(month)
        val dayIsValid = dayValidation(day, year, month)

        LaunchedEffect(yearIsValid, monthIsValid, dayIsValid) {
            val eachValid = yearIsValid && monthIsValid && dayIsValid

            if (!eachValid) {
                isDateValid = false
                return@LaunchedEffect
            }

            val dateValid: Boolean =
                try {
                    nowPersian()
                        .apply { set(year.toInt(), month.toInt(), day.toInt()) }

                    true

                } catch (e: Exception) {
                    false
                } catch (e: IllegalArgumentException) {
                    false
                }


            isDateValid = dateValid
        }

        LaunchedEffect(year, month, day) {

            try {
                if (isDateValid) {

                    val newDate = makeDate(year, month, day)

                    if (dateState != newDate) {
                        dateState = newDate
                    }
                }

            } catch (_: Exception) {
            } catch (_: IllegalArgumentException) {
            }


        }



        Row {

            Text("تاریخ شروع")

            Filler()

            val yearColor = if (!isDateValid || !yearIsValid) MaterialTheme.colorScheme.error else Color.Unspecified
            val monthColor = if (!isDateValid || !monthIsValid) MaterialTheme.colorScheme.error else Color.Unspecified
            val dayColor = if (!isDateValid || !dayIsValid) MaterialTheme.colorScheme.error else Color.Unspecified

            Text(
                year.let { if (it.length < 4) it.padStart(4, '_') else it },
                color = yearColor
            )
            Text("-")

            Text(
                month,
                color = monthColor
            )
            Text("-")

            Text(
                day,
                color = dayColor

            )
        }

        Row {

            Weight(2f) {

                TextField(
                    value = year,
                    onValueChange = {

                        try {
                            val n = it.toInt()

                            year = if (n < 1) "1" else it

                        } catch (_: Exception) {
                        }
                    },
                    label = { Text("سال") }
                )
            }
            Weight(1f) {
                TextField(
                    value = month,
                    onValueChange = {

                        try {

                            val n = it.toInt()

                            if (n < 1)
                                month = "1"
                            else if (n > 12)
                                month = "12"
                            else
                                month = it


                        } catch (_: NumberFormatException) {
                        }
                    },
                    label = { Text("ماه") })
            }
            Weight(1f) {
                TextField(
                    day,
                    {
                        try {

                            val n = it.toInt()

                            if (n < 1)
                                day = "1"
                            else
                                day = it


                        } catch (_: NumberFormatException) {
                        }
                    },
                    label = { Text("روز") }
                )
            }
        }
    }
}

fun yearValidation(year: String) =
    year.let {

        try {
            val n = it.toInt()

            n > 0

        } catch (e: NumberFormatException) {
            false
        }
    }


fun monthValidation(month: String) =
    month.let {

        try {
            val n = it.toInt()

            n in 1..12

        } catch (e: NumberFormatException) {
            false
        }
    }


fun dayValidation(day: String, year: String, month: String) =
    day.let {
        try {
            val n = it.toInt()
            val date = makeDate(year, month, "1")

            if (n < 1)
                false
            else if (n > date.monthLength) {
                false
            } else
                true

        } catch (e: NumberFormatException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }


inline fun makeDate(year: String, month: String, day: String) =
    nowPersian()
        .apply { set(year.toInt(), month.toInt() - 1, day.toInt()) }