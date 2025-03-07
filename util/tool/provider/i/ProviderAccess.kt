package util.tool.provider.i

import androidx.compose.runtime.MutableState
import util.tool.provider.ProviderService
import util.tool.provider.provider.ProviderBase
import util.tool.provider.provider.ProviderBaseReady
import util.tool.provider.provider.ProviderHolderBase

interface ProviderAccess
//<ValueType, GetType, HolderType : ProviderHolderBase>
{

    //    fun ProviderService.getHolder(): HolderType?
    fun ProviderService.makeHolder(): ProviderHolderBase


//    fun ProviderService.register(): HolderType
//    fun ProviderService.getOrRegisterHolder(): HolderType


//    fun ProviderService.dispose()

    fun <Type> ProviderService.getState(provider: ProviderBase<Type>): Type
    suspend fun <Type> ProviderService.setState(provider: ProviderBase<Any>, value: Type)


//    fun ProviderService.depending(dependencyKey: String)

}