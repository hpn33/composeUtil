package util.log


object Logger {
    var level = 0


    fun log(message: String) {

//        val tab = buildString {
//            repeat(level) {
//                append("   |")
//            }
//
//        }
//        println("$tab$message")
    }


    fun levelUp() = level++
    fun levelDown() = level--


}


//fun log(message: String) = Logger.log(message)


fun loggerLevelUp() = Logger.levelUp()
fun loggerLevelDown() = Logger.levelDown()

inline fun loggerLevelScope(scope: () -> Unit) {

    loggerLevelUp()

    scope()

    loggerLevelDown()

}