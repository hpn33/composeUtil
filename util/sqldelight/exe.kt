package util.sqldelight

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import java.sql.Driver

inline fun SqlDriver.execute(query: String) =
    execute(null, query, 0)
