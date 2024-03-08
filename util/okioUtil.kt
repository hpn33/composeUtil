package util

import okio.*
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





fun okioCopyPasteFile(from: String, to: String) {

    val copyFilePath = File(from)
    val pasteFilePath = File(to)

    // Read the original file
    val source = copyFilePath.source().buffer()

    // Write to the new file
    val sink = pasteFilePath.sink().buffer()

    // Copy the content
    sink.writeAll(source)

    // Close the sources and sinks to release resources
    source.close()
    sink.close()

}