package util.sqldelight

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import hpn.routine.Database

fun migrateIfNeeded(
    driver: SqlDriver,
    newVersion: Long = Database.Schema.version,
    migrateAct: (SqlDriver, Long, Long) -> Unit
) {

    val oldVersion = driver.getDbVersion() ?: 0

    println("DB:version = $oldVersion")

    if (oldVersion == 0L) {

        println("Creating DB version $newVersion!")
        Database.Schema.create(driver)

        driver.setDbVersion(newVersion)


    } else if (oldVersion < newVersion) {

        println("Migrating DB from version $oldVersion to $newVersion!")
        migrateAct(driver, oldVersion, newVersion)

        driver.setDbVersion(newVersion)

    }
}


private const val versionPragma = "user_version"

fun SqlDriver.setDbVersion(newVersion: Long) =
    execute("PRAGMA $versionPragma=$newVersion")


fun SqlDriver.getDbVersion() =
    executeQuery(
        null,
        "PRAGMA $versionPragma",
        { QueryResult.Value(it.getLong(0)) },
        0
    ).value
