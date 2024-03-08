package util.sqldelight

import app.cash.sqldelight.Query
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver


class FieldFactorScope {

    val fields = mutableListOf<String>()

    fun integer(name: String) {
        fields.add("$name INTEGER NOT NULL")
    }

    fun field(field: String) {
        fields.add(field)
    }

}

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
    execute(0, "ALTER TABLE $from RENAME TO $to", 0)


fun SqlDriver.dropTable(table: String) =
    execute(0, "DROP TABLE $table ", 0)

fun SqlDriver.dropColumn(table: String, column: String) =
    execute(0, "ALTER TABLE $table DROP COLUMN $column", 0)


fun <R : Any> SqlDriver.query(query: String, mapper: (SqlCursor) -> R) =
    Query(0, this, query, mapper)


fun <R : Any> SqlDriver.selectAll(table: String, mapper: (SqlCursor) -> R) =
    query("SELECT * FROM $table", mapper)
