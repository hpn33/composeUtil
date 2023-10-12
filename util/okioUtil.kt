package util

import okio.FileSystem
import okio.IOException
import okio.Path.Companion.toOkioPath
import okio.Path.Companion.toPath
import java.io.File


fun okioPathExists(filePath: String) =
    okioPathExists(File(filePath))

fun okioPathExists(filePath: File) =
    FileSystem.SYSTEM.exists(filePath.toOkioPath())

fun okioDelete(dir: String): Boolean? {

    if (!okioPathExists(dir)) {
        return false
    }


    return try {

        FileSystem.SYSTEM.deleteRecursively(dir.toPath(), true)

        true
    } catch (e: IOException) {
        null
    }
}

fun okioMakeDir(dir: String) = okioMakeDir(File(dir))
fun okioMakeDir(dir: File): Boolean? {

    if (okioPathExists(dir)) {
        return false
    }


    return try {

        FileSystem.SYSTEM.createDirectory(dir.toOkioPath())

        true

    } catch (e: IOException) {
        null
    }
}

fun okioMakeFile(dir: String, fileName: String, content: String = ""): Boolean {

    val mustCreate = !okioPathExists(dir)

    return try {

        FileSystem.SYSTEM.write(
            file = ("$dir/$fileName").toPath(),
            mustCreate = mustCreate
        ) {
            writeUtf8(content)
        }

        true
    } catch (e: IOException) {
        false
    }
}



