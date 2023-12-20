package util.sqldelight

import app.cash.sqldelight.Query
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver


private const val versionPragma = "user_version"
//private const val versionPragma = "dbver"

fun SqlDriver.setDbVersion(newVersion: Long): QueryResult<Long>? {
    println("the db version $newVersion")
    return try {

        Query(0, this, "PRAGMA $versionPragma=$newVersion;") { QueryResult.Value(0L) }
            .executeAsOne()
    } catch (e: Exception) {
        println(e)
        null
    }
}


fun SqlDriver.getDbVersion() =
    try {
        Query(0, this, "PRAGMA $versionPragma;") { QueryResult.Value(it.getLong(0)) }.executeAsOne().value
            ?: throw Exception("return null")
    } catch (e: Exception) {
        println(e)
        null
    }

