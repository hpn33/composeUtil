package util.kot.collect


fun <E> List<E>.subFirstRange(count: Int): List<E> {

    if ((count) > (this.size - 1))
        throw Exception("the input parameter in out of range ($count)")

    return buildList {

        var counter = 0

        for (item in this@subFirstRange) {
            counter += 1
            add(item)

            if (counter > count) {
                break
            }
        }
    }
}


fun <E> List<E>.subFirstRangeOrLess(count: Int): List<E> {

    return buildList {

        var counter = 0

        for (item in this@subFirstRangeOrLess) {
            counter += 1
            add(item)

            if (counter > count) {
                break
            }
        }
    }
}

fun <E> List<E>.subRange(startIndex: Int, count: Int): List<E> {

    if ((startIndex + count) > (this.size - 1))
        throw Exception("the input parameter in out of range ($startIndex -> $count)")

    return buildList {

        var counter = 0

        for (index in startIndex..<this@subRange.size) {

            if (counter < count) {
                add(this[index])
                counter += 1
            }
        }
    }
}


fun <E> List<E>.subRangeOrLess(startIndex: Int, count: Int): List<E> {

    if ((startIndex) > (this.size - 1))
        throw Exception("the input parameter in out of range ($startIndex -> $count)")

    return buildList {

        var counter = 0

        for (index in startIndex..<this@subRangeOrLess.size) {

            if (counter < count) {
                add(this[index])
                counter += 1
            }
        }
    }
}
