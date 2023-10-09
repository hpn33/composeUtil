package util.composeUtil.hero

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import composeUtil.provider.ProviderRef
import composeUtil.provider.ProviderService


typealias ComposableFunc = @Composable () -> Unit


class HeroService {
    fun addContent(tag: String, content: ComposableFunc) {

        if (!contents.containsKey(tag)) {
            contents[tag] = mutableListOf()
        }

        contents[tag]!!.add(content)

    }


    val contents = mutableMapOf<String, MutableList<ComposableFunc>>()


}


val LocalHeroScope = compositionLocalOf<HeroService> { error("not implemented") }

@Composable
inline fun getHeroRef() = LocalHeroScope.current

@Composable
fun HeroScope(content: @Composable () -> Unit) {

    CompositionLocalProvider(LocalHeroScope provides HeroService()) {

        content()

    }

}


@Composable
fun hero(tag: String, content: ComposableFunc) {

    val heroRef = getHeroRef()

    heroRef.addContent(tag, content)

}