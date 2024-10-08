package util.kot.collect

fun <T> List<T>.moveItem(find: (T) -> Boolean, newIndex: (Int) -> Int): List<T> {

    val existIndex = indexOfFirst(find)

    if (existIndex == -1) {
        return this
    }

    val item = this[existIndex]
    val theNewIndex = newIndex(existIndex)

    if (theNewIndex < 0) {
        throw IndexOutOfBoundsException(theNewIndex)
    }

    val newList = buildList {

        addAll(this@moveItem)

        removeAt(existIndex)
        add(newIndex(existIndex), item)
    }

    return newList
}
