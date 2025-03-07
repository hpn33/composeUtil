package util.tool.provider.test

import util.tool.provider.helper.ProviderScopeTracker
import util.tool.provider.helper.ScopeState
import util.tool.provider.helper.activeScope

// active scope
// deactivate scope


val listProviderTest = listOf(
    ::`add one scope`,
    ::`add two scope`,
    ::`add three scope`,

    ::`add three free scope`,
)
//

fun ifOrNull(condition: () -> Boolean): (() -> Unit)? {
    if (condition()) return {}

    return null
}

fun `add one scope`() {

    println("--------------------")
    println("test: add one scope")

    val tracker = ProviderScopeTracker()

    // add
    tracker.active("scope A", true)

    // check the list

    ifOrNull { tracker.activeScope?.name == "scope A" } ?: return
    ifOrNull { tracker.list.any { it.name == "scope A" } } ?: return


    println(">>pass<<")
}


fun `add two scope`() {

    println("--------------------")
    println("test: add two scope")

    val tracker = ProviderScopeTracker()

    // add
    tracker.active("scope A", true)
    tracker.active("scope B", true)

    // check the list

    ifOrNull { tracker.list.last().name == "scope B" } ?: return
    ifOrNull { tracker.list.last().state == ScopeState.Active } ?: return

    ifOrNull { tracker.previousScope?.state == ScopeState.Pause } ?: return

    println(">>pass<<")
}


fun `add three scope`() {

    println("--------------------")
    println("test: add three scope")

    val tracker = ProviderScopeTracker()

    // add
    tracker.active("scope A", true)
    tracker.active("scope B", true)
    tracker.active("scope C", true)

    // check the list

    ifOrNull { tracker.list.last().name == "scope C" } ?: return
    ifOrNull { tracker.list.last().state == ScopeState.Active } ?: return

    ifOrNull { tracker.previousScope?.state == ScopeState.Pause } ?: return



    ifOrNull { tracker.actualCurrentScope?.name == "scope C" } ?: return
    ifOrNull { tracker.actualCurrentScope?.state == ScopeState.Active } ?: return


    ifOrNull { tracker.actualPreviousScope?.name == "scope B" } ?: return
    ifOrNull { tracker.actualPreviousScope?.state == ScopeState.Pause } ?: return

    println(">>pass<<")
}

fun `add three free scope`() {

    println("--------------------")
    println("test: add three free scope")

    val tracker = ProviderScopeTracker()

    // add
    tracker.active("scope A", false)
    tracker.active("scope B", false)
    tracker.active("scope C", false)

    // check the list

    ifOrNull { tracker.currentScope?.name == "scope C" } ?: return
//    ifOrNull { tracker.currentScope?.state != ScopeState.Active } ?: return

    ifOrNull { tracker.previousScope?.state == ScopeState.Free } ?: return


    ifOrNull { tracker.actualCurrentScope == null } ?: return
    ifOrNull { tracker.actualPreviousScope == null } ?: return


    println(">>pass<<")
}