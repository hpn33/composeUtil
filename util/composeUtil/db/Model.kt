package util.composeUtil.db

abstract class Model {

    abstract fun delete()
    abstract fun save()

}

abstract class TableModel<T : Model>(val tableName: String) {

    abstract fun delete(obj: T)

}

//fun TableModel<*>.tableIdTag(id: Long?) = tableName + "Id" + id


abstract class QueryModel<T>(val tableName: String)