package util.string

val englishDigits = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
val persianDigits = listOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')


inline fun <T : Any?> T.print(): T = also { println(this) }

//@Composable
//inline fun String.text() = Text(this)


//fun convertEnglishToPersian(input: Any?): String =
//    when (input) {
//        is Int -> convertEnglishToPersian(input)
//        is Float -> convertEnglishToPersian(input)
//
//        else -> input.toString()
//    }

object FarsiUtil {

    fun convertNumber(number: Int) =
        buildString {
            for (char in number.toString()) {

                val newChar = persianDigits[englishDigits.indexOf(char)]

                append(newChar)
            }
        }

    fun convertNumber(number: Float) =
        buildString {
            for (char in number.toString()) {

                val newChar = persianDigits.getOrNull(englishDigits.indexOf(char)) ?: char

                append(newChar)
            }
        }


    fun convertNumber(input: String) =
        buildString {
            for (char in input) {

                val newChar = persianDigits.getOrNull(englishDigits.indexOf(char)) ?: char

                append(newChar)
            }
        }
}


inline fun String.numToFarsi() = FarsiUtil.convertNumber(this)
inline fun Int.numToFarsi() = FarsiUtil.convertNumber(this)
inline fun Float.numToFarsi() = FarsiUtil.convertNumber(this)
