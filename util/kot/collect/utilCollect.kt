package util.kot.collect

fun <T> Iterable<T>.unique(filter: (T, T) -> Boolean) =
    buildList {
        this@unique.forEach { item ->
            if (!this.any { i -> filter(i, item) }) {
                add(item)
            }
        }
    }


data class CollectUtil<T1, T2>(
    val first: List<T1>,
    val second: List<T2>,
)

fun <T1, T2> CollectUtil<T1, T2>.include(filter: (T1, T2) -> Boolean) =
    first.include(second, filter)

fun <T1, T2> CollectUtil<T1, T2>.exclude(filter: (T1, T2) -> Boolean) =
    first.exclude(second, filter)


fun <T1, T2> List<T1>.include(second: List<T2>, filter: (T1, T2) -> Boolean) =
    buildList {

        this@include.forEach { i1 ->
            if (second.any { i2 -> filter(i1, i2) }) {
                add(i1)
            }
        }
    }

fun <T1, T2> List<T1>.exclude(list: List<T2>, filter: (T1, T2) -> Boolean) =
    buildList {

        this@exclude.forEach { i1 ->
            if (!list.any { i2 -> filter(i1, i2) }) {
                add(i1)
            }
        }
    }




