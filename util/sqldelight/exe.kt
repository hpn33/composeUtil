package util.sqldelight

import app.cash.sqldelight.db.SqlDriver

inline fun SqlDriver.queryExecute(query: String) =
    execute(null, query, 0)
