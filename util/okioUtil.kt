package util

import okio.FileSystem
import okio.Path.Companion.toPath

fun deleteIo(dir: String) {

    if (!FileSystem.SYSTEM.exists(dir.toPath())) return

    FileSystem.SYSTEM.deleteRecursively(dir.toPath(), true)
}

fun makeDir(dir: String) {

    if (FileSystem.SYSTEM.exists(dir.toPath())) return

    FileSystem.SYSTEM.createDirectory(dir.toPath())
}

fun makeFile(dir: String, fileName: String, content: String = "") {

    val mustCreate = !FileSystem.SYSTEM.exists(dir.toPath())

    FileSystem.SYSTEM.write(
        file = ("$dir/$fileName").toPath(),
        mustCreate = mustCreate
    ) {
        writeUtf8(content)
    }
}

object OkioUtil {

    fun pathExists(filePath: String) =
        FileSystem.SYSTEM.exists(filePath.toPath())

}