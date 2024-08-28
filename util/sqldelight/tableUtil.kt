package util.sqldelight


abstract class FieldBase(
    val name: String,
    val type: String,
    val context: String = ""
)

class Field(name: String, context: String = "") : FieldBase(name, context)

class FieldString(name: String) : FieldBase(name, "TEXT", "NOT NULL")
class FieldLong(name: String) : FieldBase(name, "INTEGER", "NOT NULL")


fun tableInfo(
    name: String,
    fieldMaker: FieldScope.() -> Unit = {},
): TableInfo {

    val fieldScope = FieldScope
    fieldScope.fieldMaker()


    return TableInfo(name, FieldScope.fields)
}


data class TableInfo(
    val name: String,
    val fields: List<FieldBase>,
)

val tableSchema =
    tableInfo("schema") {
        long("id")
        string("hello")
    }


object FieldScope {
    var fields = mutableListOf<FieldBase>()


    fun FieldScope.field(name: String, context: String) {
        fields.add(Field(name, context))
    }

    fun FieldScope.string(name: String) {
        fields.add(FieldString(name))
    }

    fun FieldScope.long(name: String) {
        fields.add(FieldLong(name))
    }

    fun FieldScope.bytes(name: String) {}
    fun FieldScope.double(name: String) {}
    fun FieldScope.boolean(name: String) {}

}


//val tableSubjects =
//    tableInfo("Subjects") {
//        long("id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL")
//
//        string("title TEXT NOT NULL")
//        string("description TEXT NOT NULL")
//
//        long("color")
//        long("active")
//
//        long("create_at")
//        long("update_at")
//    }



