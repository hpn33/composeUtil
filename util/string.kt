package util


fun Long.toStringFix(size: Int): String {

    val n = this.toString().reversed()
    val nLength = n.length

    val nCount = if (nLength > size) size else nLength
    val nFix = if (nLength > size) 0 else (size - nLength)


    val text =
        buildList {

            repeat(nCount) {
                add(n[it].toString())
            }

            repeat(nFix) {
                add("0")
            }

        }
            .reversed()
            .joinToString("") { it }

    return text
}

fun Float.toStringFix(size: Int): String {

    val n = this.toString().split(".")

    val text =
        buildList {

            add(n.first())

            add(".")

            repeat(size) {
                val a = n[1].getOrNull(it) ?: '0'

                add(a.toString())
            }

        }
            .joinToString("") { it }

    return text
}