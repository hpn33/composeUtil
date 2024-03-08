package util.sqldelight

import app.cash.sqldelight.db.SqlDriver


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

fun SqlDriver.queryGetTables() =
    query(getTables()) {
        it.getString(0) ?: "---"
    }
        .executeAsList()


fun getColumns(table: String): String {
    return "PRAGMA table_info($table)"
}


fun SqlDriver.queryGetColumns(table: String) =
    query(getColumns(table)) {
        it.getString(1) ?: "---"
    }
        .executeAsList()


fun SqlDriver.querySchema() =
    queryGetTables()
        .associateWith { queryGetColumns(it) }