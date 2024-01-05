package test.provider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import kotlinx.coroutines.delay
import util.compose.provider.ProviderScope
import util.compose.provider.holder.SuspendState
import util.compose.provider.provider.Provider
import util.compose.provider.provider.SuspendProvider
import util.compose.provider.useProvider
import util.compose.provider.useProviderAccess


data class Todo(
    val title: String,
    val check: Boolean = false
)

val todosProvider = Provider("todos") {
    listOf<Todo>(
        Todo("title1"),
        Todo("title2", check = true),
        Todo("title3"),
        Todo("title4", check = true),
    )
}


enum class TodoFilter {
    All, Done, Undone
}

val filterProvider = Provider("filter") { TodoFilter.All }

val searchProvider = Provider("search") { "" }

val todosFilteredProvider = SuspendProvider("filtered") {

    val filter = get(filterProvider)
    val todos = get(todosProvider)

    delay(500)
    println("filter [${filter.name}]")

    when (filter) {
        TodoFilter.All -> todos
        TodoFilter.Done -> todos.filter { it.check }
        TodoFilter.Undone -> todos.filter { !it.check }
    }

}

val todosSearchedProvider = SuspendProvider("searched") {

    val searchInput = get(searchProvider)
    val filteredList = get(todosFilteredProvider)

    delay(500)
    println("search [$searchInput]")

    when (filteredList) {
        is SuspendState.Data -> {
            if (searchInput.isBlank()) {
                filteredList.data
            } else
                filteredList.data?.filter { it.title.contains(searchInput) }
        }

        is SuspendState.Error -> listOf()
        is SuspendState.Loading -> listOf()
    }

}


val todosFinalProvider = SuspendProvider<List<Todo>?>("final") {

    val a = get(todosSearchedProvider)

    when (a) {
        is SuspendState.Data -> {
            println("final (${a.data?.size})")
            a.data
        }

        is SuspendState.Error -> listOf()
        is SuspendState.Loading -> listOf()
    }

}


//val todosFinalProvider = Provider("final") {
//
//    val todos = get(todosProvider)
//    val filter = get(filterProvider)
//    val searchInput = get(searchProvider)
//
//    println("filter [${filter.name}]")
//
//    val f = when (filter) {
//        TodoFilter.All -> todos
//        TodoFilter.Done -> todos.filter { it.check }
//        TodoFilter.Undone -> todos.filter { !it.check }
//    }
//
//
//
//
//    if (searchInput.isBlank()) {
//        return@Provider f
//    }
//
//    f.filter { it.title.contains(searchInput) }
//
//}


@Composable
fun ProviderTodoView() {

    ProviderScope {
        println("------------ main")

        var todos by useProviderAccess(todosProvider)
        var filter by useProviderAccess(filterProvider)
        var searchInput by useProviderAccess(searchProvider)


        Column {


            Row {
                SearchBox()
                Button({
                    todos = buildList {

                        addAll(todos)

                        add(Todo(searchInput))

                    }

                    searchInput = ""
                }) {
                    Text("Add")
                }

            }


            Row {

                TodoFilter.entries.forEach {

                    Button({
                        filter = it

                    }) {

                        Text(it.name)
                    }


                }
            }



            ShowList()
        }

        println("------------ main end")

    }

}

@Composable
fun SearchBox(modifier: Modifier = Modifier) {

    println("------------ searchBox")

    var search by useProvider(searchProvider)

    TextField(
        search,
        { search = it },
        modifier
    )

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowList() {

    println("------------ showList")

    var todos by useProviderAccess(todosProvider)
    val todosFinal by useProvider(todosFinalProvider)

    LazyColumn {

        todosFinal
            .on(
                error = { item { Text("notLoda..d :${it}") } },
                loading = { item { CircularProgressIndicator() } }
            ) { data ->

                if (data == null) {
                    item {

                        Text("is null")
                    }

                    return@LazyColumn
                }


                if (data.isEmpty()) {
                    item {

                        Text("Empty")
                    }

                    return@LazyColumn
                }


                items(data) {

                    Box(
                        Modifier.alpha(if (it.check) .5f else 1f)
                    ) {

                        Card({

                            val newTodo = it.copy(check = !it.check)

                            val newList = buildList {

                                addAll(data.filterNot { i -> i == it })

                                add(newTodo)
                            }

                            todos = newList

                        }) {
                            Text(it.title)
                        }
                    }

                }
            }

    }
}
