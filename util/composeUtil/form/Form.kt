package util.composeUtil.form

import androidx.compose.foundation.layout.Column
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable


/**
 * - [x] field
 *  - [ ] field config-setting
 * - [ ] action bar
 * - [ ] other data type
 * - [ ] style control
 * - [ ]
 */
@Composable
fun example() {

    form {

        field("title")

        field(
            "description",
//            config = config(
//                maxLine = 5
//            )
        )

        field(
            "date",
//            config(
//                enabled = false,
//            ),
//            modifier = Modifier.clickable { }
        )
    }

}

class FormScope {

    val elements = mutableListOf<FormElement>()
    val data = mutableMapOf<String, FormData>()
    val validators = mutableMapOf<String, FormDataValidation>()

    fun field(
        name: String,
        default: String = "",
        validate: (String) -> Boolean = { true },
    ): Boolean {

        val item = FormField(name)
        item.ref = this

        data[name] = FormData(name, default)
        validators[name] = FormDataValidation(name, validate)

        return elements.add(item)
    }

    @Composable
    fun build() {

        Column {

            elements.forEach { it.build() }

        }


    }

    fun getData(name: String) = data[name]?.value ?: ""
    fun validate(name: String, value: String): String? =
        if (validators[name]?.validator?.let { it(value) } == true)
            value
        else
            null

    fun setValue(name: String, value: String) {
        data[name]?.value = value
    }

}

data class FormData(val name: String, var value: String)
data class FormDataValidation(val name: String, val validator: (String) -> Boolean)

abstract class FormElement(val name: String) {


    lateinit var ref: FormScope

    @Composable
    abstract fun build()
}

class FormField(name: String) : FormElement(name) {
    @Composable
    override fun build() {
        TextField(
            ref.getData(name),
            {
                val validate = ref.validate(name, it)
                if (validate != null) {
                    ref.setValue(name, validate)
                }
            }
        )
    }

}


@Composable
fun form(content: FormScope.() -> Unit) {

    val scope = FormScope()

    scope.content()

    scope.build()

}

