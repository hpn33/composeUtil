package util.sqldelight

import hpn.routine.data.db.makeBackupDb
import hpn.routine.data.db.restoreBackup
import share.db.DriverFactory


inline fun MigrationScope.migration(
    factory: DriverFactory,
    version: Long,
    from: Int,
    function: QueryScope.() -> Unit
) {


    if (version.toInt() == from) {

        val backup = makeBackupDb(version.toString(), factory.dbPath())

        try {
            println("migration from $version")
            with(QueryScope(sqlDriver)) {
                function()
            }

        } catch (e: Exception) {

            println("Fail $e")
            restoreBackup(factory, backup)

        }

//        okioDelete(backup)

    }

}

private inline fun MigrationScope.migrationNoBackUpDebug(
    factory: DriverFactory,
    version: Long,
    from: Int,
    function: QueryScope.() -> Unit
) {


    if (version.toInt() == from) {

//        val backup = makeBackupDb(version.toString(), factory.dbPath())

        try {
            println("migration from $version")
            with(QueryScope(sqlDriver)) {
                function()
            }

        } catch (e: Exception) {

            println("Fail $e")
//            restoreBackup(factory, backup)

        }

//        okioDelete(backup)

    }

}


class Migrate {


    fun up() {}
    fun tearDown() {}

    fun rollback() {}
    fun storePoint() {}

}
