package util.sqldelight

import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver


val defaultTable = listOf(
    "sqlite_master",
    "sqlite_schema",
    "sqlite_temp_schema",
    "sqlite_temp_master",
)


data class Row_SqliteMaster(
    val type: String,
    val name: String,
    val tblName: String,
    val rootPage: Long,
    val sql: String,
)

class Table_SqliteMaster {

    val tableName = "sqlite_schema"

    val fields = listOf("")


    fun get(driver: SqlDriver): List<Row_SqliteMaster> {
        return driver.selectAll(tableName, ::convert).executeAsList()
    }

    fun convert(cursor: SqlCursor): Row_SqliteMaster {
        return Row_SqliteMaster(
            cursor.getString(0) ?: "",
            cursor.getString(1) ?: "",
            cursor.getString(2) ?: "",
            cursor.getString(3)?.toLong() ?: -1L,
            cursor.getString(4) ?: "",
        )
    }

}


fun getTables(): String {

    return """
SELECT  
    name
FROM  
    sqlite_schema
WHERE  
    type ='table' AND  
    name NOT LIKE 'sqlite_%';
        """.trimIndent()
}

fun QueryScope.queryGetTables() =
    sqlDriver.queryResult(getTables()) {
        it.getString(0) ?: "---"
    }
        .executeAsList()


fun getColumns(table: String): String {
    return "PRAGMA table_info($table)"
}


fun QueryScope.queryGetColumns(table: String) =
    sqlDriver.queryResult(getColumns(table)) {
        it.getString(1) ?: "---"
    }
        .executeAsList()


fun QueryScope.querySchema() =
    queryGetTables()
        .associateWith { queryGetColumns(it) }