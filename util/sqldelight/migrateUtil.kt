package util.sqldelight

import app.cash.sqldelight.Query
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlPreparedStatement
import kotlin.random.Random


class FieldFactorScope {

    val fields = mutableListOf<String>()

    fun integer(name: String) {
        fields.add("$name INTEGER NOT NULL")
    }

    fun field(field: String) {
        fields.add(field)
    }

}


inline fun SqlDriver.exe(query: String): QueryResult<Long> {
    return execute(null, query, 0)
}

inline fun SqlDriver.exe(query: String, parameters: Int, noinline binder: SqlPreparedStatement.() -> Unit): QueryResult<Long> {
    return execute(null, query, parameters, binder)
}


//class BinderScope {
//    var counter = 0
//
//    val fields = mutableListOf<Any>()
//
//    fun bindBytes(index: Int, bytes: ByteArray?) {
//        counter += 1
//
//    }
//
//    fun bindLong(index: Int, long: Long?) {}
//
//    fun bindDouble(index: Int, double: Double?) {}
//
//    fun bindString(index: Int, string: String?) {}
//
//    fun bindBoolean(index: Int, boolean: Boolean?) {}
//
//    fun SqlPreparedStatement.prepared() {
//
//    }
//
//}

//inline fun SqlDriver.exe(query: String, binder: BinderScope.() -> Unit): QueryResult<Long> {
//
//    val binderScope = BinderScope()
//
//    binderScope.binder()
//
//    return execute(null, query, binderScope.counter, binderScope.prepared)
//}

fun <R : Any> SqlDriver.exeQuery(query: String, mapper: (SqlCursor) -> R) =
    Query(Random.nextInt(10000000), this, query, mapper)


fun SqlDriver.createTable(
    table: String,
    fieldFactor: FieldFactorScope.() -> Unit
): QueryResult<Long> {

    val scope = FieldFactorScope()
    scope.fieldFactor()

    val fields = scope.fields.joinToString { it }

    return execute(null, "CREATE TABLE IF NOT EXISTS $table ($fields);", 0)
}

fun SqlDriver.renameTable(from: String, to: String) =
    exe("ALTER TABLE $from RENAME TO $to")


fun SqlDriver.dropTable(table: String) =
    exe("DROP TABLE $table ")

fun SqlDriver.dropColumn(table: String, column: String) =
    exe("ALTER TABLE $table DROP COLUMN $column")


fun <R : Any> SqlDriver.selectAll(table: String, mapper: (SqlCursor) -> R) =
    exeQuery("SELECT * FROM $table", mapper)

fun SqlDriver.count(table: String, whereCondition: String = ""): Int {

    val query = "SELECT COUNT(*) FROM $table"

    val where = if (whereCondition.isEmpty()) "" else "WHERE $whereCondition"

    return exeQuery("$query $where") { 0 }.executeAsList().size
}