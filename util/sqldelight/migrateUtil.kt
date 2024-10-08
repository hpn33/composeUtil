package util.sqldelight

import app.cash.sqldelight.Query
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlPreparedStatement
import share.db.DriverFactory
import kotlin.random.Random


class MigrationScope(val sqlDriver: SqlDriver, val factory: DriverFactory)

@JvmInline
value class QueryScope(val sqlDriver: SqlDriver) {
    inline fun <T : Any> transition(action: SqlDriver.() -> T): T {
        return sqlDriver.action()
    }
}

inline fun <T> SqlDriver.queryScope(action: QueryScope.() -> T): T =
    with(QueryScope(this)) {
        action()
    }


class FieldFactorScope {

    val fields = mutableListOf<String>()

    fun integer(name: String) {
        fields.add("$name INTEGER NOT NULL")
    }

    fun string(name: String) {
        fields.add("$name TEXT NOT NULL")
    }

    fun field(field: String) {
        fields.add(field)
    }

}


inline fun SqlDriver.queryAction(query: String): QueryResult<Long> {
    return execute(null, query, 0)
}

inline fun SqlDriver.queryAction(
    query: String,
    parameters: Int,
    noinline binder: SqlPreparedStatement.() -> Unit
): QueryResult<Long> {
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

fun <R : Any> SqlDriver.queryResult(query: String, mapper: (SqlCursor) -> R) =
    Query(Random.nextInt(10000000), this, query, mapper)

fun QueryScope.createTable(
    tableInfo: TableInfo,
): QueryResult<Long> {

    val tableName = tableInfo.name
    val fields = tableInfo.fields.map { "${it.name} ${it.context}" }.joinToString { it }

    return sqlDriver.execute(null, "CREATE TABLE IF NOT EXISTS $tableName ($fields);", 0)
}


fun QueryScope.createTable(
    table: String,
    fieldFactor: FieldFactorScope.() -> Unit
): QueryResult<Long> {

    val scope = FieldFactorScope()
    scope.fieldFactor()

    val fields = scope.fields.joinToString { it }

    return sqlDriver.execute(null, "CREATE TABLE IF NOT EXISTS $table ($fields);", 0)
}

fun QueryScope.renameTable(from: String, to: String) =
    sqlDriver.queryAction("ALTER TABLE $from RENAME TO $to")


fun QueryScope.dropTable(table: String) =
    sqlDriver.queryAction("DROP TABLE $table ")

fun QueryScope.dropColumn(table: String, column: String) =
    sqlDriver.queryAction("ALTER TABLE $table DROP COLUMN $column")


fun <R : Any> QueryScope.selectAll(table: String, mapper: (SqlCursor) -> R) =
    sqlDriver.queryResult("SELECT * FROM $table", mapper)

fun <R : Any> SqlDriver.selectAll(table: String, mapper: (SqlCursor) -> R) =
    queryResult("SELECT * FROM $table", mapper)

fun QueryScope.count(table: String, whereCondition: String = ""): Int {

    val query = "SELECT COUNT(*) FROM $table"

    val where = if (whereCondition.isEmpty()) "" else "WHERE $whereCondition"

    return sqlDriver.queryResult("$query $where") { 0 }.executeAsList().size
}