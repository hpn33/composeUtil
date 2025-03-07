package util.tool.provider

import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelScope
import util.log.loggerLevelUp
import util.tool.provider.provider.ProviderBase
import util.tool.provider.provider.ProviderBaseReady
import util.tool.provider.provider.ProviderHolderBase
import util.tool.provider.provider.normal.Provider


enum class ProviderType {
    Normal,
    Suspend,
    Compose
}


class ProviderService {

    val providerHolderDB = ProviderHolderDB()


    /// 0-------------------------------------------------
    /// 0-------------------------------------------------
    /// 0-------------------------------------------------


    fun registerProvider(provider: ProviderBaseReady<*, *, *>): ProviderHolderBase {
        val holder = with(provider) { makeHolder() }

        providerHolderDB.add(holder)

        return holder
    }


    fun getProviderHolder(provider: ProviderBase<*>): ProviderHolderBase? {
        return this.providerHolderDB.getHolder(provider)
    }


    fun getOrRegisterProviderHolder(provider: ProviderBase<*>): ProviderHolderBase {

        val ph = getProviderHolder(provider)


        if (ph == null) {

            log("[providerService] [register] ${provider}")

            val p = registerProvider(provider as ProviderBaseReady<*, *, *>)
            p.load()



            return p
        }


//        log("[providerService] [get] $provider")

        return ph
    }


    fun dispose(provider: ProviderBase<*>) {

        providerHolderDB.remove(provider)

    }

    fun <GetType> observeValue(provider: Provider<GetType>, action: (GetType) -> Unit) {
//        log("[providerService] [getState] $provider")

        loggerLevelUp()
        val ph = getOrRegisterProviderHolder(provider)
        ph.subscribe.add(action as (Any) -> Unit)
        loggerLevelDown()

    }


    fun <GetType> getValue(provider: ProviderBase<GetType>): GetType {
//        log("[providerService] [getState] $provider")

        loggerLevelUp()
        val ph = getOrRegisterProviderHolder(provider)
        loggerLevelDown()

        return ph.value as GetType
    }

    suspend fun <Type> setValue(provider: ProviderBase<Type>, value: Type) {


        log("[providerService] [setState] ${provider}")

        val holder = getOrRegisterProviderHolder(provider)
        val oldState = holder.value

        loggerLevelScope {

//            log("[providerService] value ${state.value}")
            holder.value = value as Any
            log("[providerService] value ${oldState} ${holder.value}")
            watcherAction(provider as Provider<Any>, oldState as Any, value as Any)

            recomputeDependents(provider.key)

        }
    }


    fun depending(provider: ProviderBaseReady<*, *, *>, dependencyKey: String) {

//        log("[providerService] [depending] $provider")

        val dependency = getProviderHolder(provider)!!.dependencies

        if (!dependency.contains(dependencyKey)) {
            log("[providerService] [depend] ${provider}")
            dependency.add(dependencyKey)
        }

//        log(dependency.toString())

    }

    fun recomputeDependents(providerKey: String) {
        log("[providerService] [recomputeDependents] $providerKey")

        loggerLevelScope {

            providerHolderDB.getHolderByDependencyKey(providerKey)
                .also { log("dep: ${it.map { it.key }}") }
                .forEach {

                    log("${it.providerType}: ${it.key}")

                    loggerLevelScope {

//                        if (hasDeepUse(it)) {
                        it.revalue()
                        recomputeDependents(it.key)
//                        }
                    }
                }

        }
    }


    private fun hasDeepUse(providerHolderBase: ProviderHolderBase): Boolean {


        log("use(${providerHolderBase.key}): " + providerHolderBase.locals.toString())

        if (providerHolderBase.locals.isEmpty())
            return false


        return providerHolderDB.getHolderByDependencyKey(providerHolderBase.key).any {
            loggerLevelUp()
            hasDeepUse(it)
                .also { loggerLevelDown() }
        }

    }

//    fun <T> getState(provider: ProviderBaseReady<*, T, *>): MutableState<T> {
//
//        loggerLevelUp()
//        val holder = with(provider) { getState() }
//        loggerLevelDown()
//
//        return holder
//
//    }

    fun localRegister(provider: ProviderBaseReady<*, *, *>, scope: String, localProviderKey: Int) {
        log("[providerService] [localRegister] ${provider} $scope $localProviderKey")
        getOrRegisterProviderHolder(provider)
            .apply {
//                activeScope.value = scope

                locals.getOrPut(scope, { mutableListOf() })
                    .add(localProviderKey)
            }
    }

    fun localRegisterRemove(provider: ProviderBaseReady<*, *, *>, scope: String, localProviderKey: Int) {
        log("[providerService] [localRegisterRemove] ${provider} $scope $localProviderKey")
        getOrRegisterProviderHolder(provider)
            .locals[scope]
            ?.remove(localProviderKey)
    }


    suspend fun <T : Any> watcherAction(provider: Provider<T>, oldValue: T, value: T) {

        providerHolderDB.getHolder(provider)?.watchers?.forEach { it.setAction(oldValue, value) }

    }
}




