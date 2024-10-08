package util.kot.collect


fun <T> List<T>.insert(item: T): List<T> {
    return this + item
}

fun <T> List<T>.insertIfNot(item: T, exist: (T) -> Boolean): List<T> {

    if (any(exist)) {
        return this
    }

    return this + item
}

fun <T> List<T>.remove(find: (T) -> Boolean): List<T> {

    val index = this.indexOfFirst(find)

    if (index == -1) {
        return this
    }

    val newList = buildList {

        addAll(this@remove)

        removeAt(index)
    }

    return newList
}


fun <T> List<T>.update(findFirst: (T) -> Boolean, itemBuilder: (T?) -> T?): List<T> {

    val existIndex = indexOfFirst(findFirst)
    val newItem =
        if (existIndex == -1) {
            itemBuilder(null)
        } else
            itemBuilder(this[existIndex])

    if (newItem == null) {
        return this
    }

    val newList =
        buildList {

            addAll(this@update)

            if (existIndex != -1) {
                removeAt(existIndex)
                add(existIndex, newItem)
            }
        }


    return newList
}


fun <T> List<T>.updateOrInsert(findFirst: (T) -> Boolean, itemBuilder: (T?) -> T?): List<T> {

    val existIndex = indexOfFirst(findFirst)
    val newItem =
        if (existIndex == -1) {
            itemBuilder(null)
        } else
            itemBuilder(this[existIndex])

    if (newItem == null) {
        return this
    }

    val newList =
        buildList<T> {

            addAll(this@updateOrInsert)

            if (existIndex != -1) {
                removeAt(existIndex)
                add(existIndex, newItem)
            } else {
                add(newItem)
            }
        }

    return newList
}


