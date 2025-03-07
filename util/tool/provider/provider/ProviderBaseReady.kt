package util.tool.provider.provider

import util.log.Logger.log
import util.log.loggerLevelDown
import util.log.loggerLevelScope
import util.log.loggerLevelUp
import util.tool.provider.ProviderService
import util.tool.provider.ProviderType
import util.tool.provider.i.ProviderAccess
import util.tool.provider.provider.normal.Provider

abstract class ProviderBaseReady<Type, GetType, HolderType : ProviderHolderBase>(
    label: String = "",
    type: ProviderType
) : ProviderBase<Type>(label, providerType = type),
//    ProviderAccess<Type, GetType, HolderType> {
    ProviderAccess {


//    override fun ProviderService.getHolder(): HolderType? {
//        return this.providerHolderDB.getHolder(this@ProviderBaseReady) as HolderType?
//    }

    abstract override fun ProviderService.makeHolder(): HolderType

//    override fun ProviderService.register(): HolderType {
//        val holder = makeHolder()
//
//        providerHolderDB.add(holder as ProviderHolderBase<*>)
//
//        return holder as HolderType
//    }

//    override fun ProviderService.getOrRegisterHolder(): HolderType {
//
//        val ph = getHolder(this@ProviderBaseReady)
//
//
//        if (ph == null) {
//
//            log("[providerService] [register] ${this@ProviderBaseReady}")
//
//            val p = register()
//            p.load()
//
//
//
//            return p
//        }
//
//
////        log("[providerService] [get] $provider")
//
//        return ph as HolderType
//    }

//    override fun ProviderService.dispose() {
//
////        log("[providerService] [getState] $provider")
//
//        loggerLevelUp()
//        val ph = registerOrGet()
//        loggerLevelDown()
//
//        providerHolderDB.remove(ph)
//    }

    override fun <Type> ProviderService.getState(provider: ProviderBase<Type>): Type {
//        log("[providerService] [getState] $provider")

        loggerLevelUp()
        val ph = getOrRegisterProviderHolder(provider)
        loggerLevelDown()

        return ph.value as Type
    }

//    override fun ProviderService.depending(dependencyKey: String) {
//
////        log("[providerService] [depending] $provider")
//
//        val dependency = getHolder()!!.dependencies
//
//        if (!dependency.contains(dependencyKey)) {
//            log("[providerService] [depend] ${this@ProviderBaseReady}")
//            dependency.add(dependencyKey)
//        }
//
////        log(dependency.toString())
//
//    }

    override suspend fun <Type> ProviderService.setState(provider: ProviderBase<Any>, value: Type) {


        log("[providerService] [setState] ${this@ProviderBaseReady}")

        val holder = getOrRegisterProviderHolder(provider)

        val oldState = holder.value

        loggerLevelScope {

//            log("[providerService] value ${state.value}")
            holder.value = value as Any
            log("[providerService] value ${oldState} ${holder.value}")
            watcherAction(this@ProviderBaseReady as Provider<Any>, oldState as Any, value as Any)

            recomputeDependents(this@ProviderBaseReady.key)

        }
    }

}
