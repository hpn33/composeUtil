package util.color

import androidx.compose.ui.graphics.Color


interface ColorSwatch {

    val default: Color
//        get() = (swatch[500] ?: swatch[200])!!

    operator fun get(index: Int): Color
//    = swatch[index]


}

@JvmInline
value class MaterialColor(val swatch: Map<Int, Color>) : ColorSwatch {

    override val default: Color
        get() = swatch[500]!!

    override fun get(index: Int) = swatch[index]!!


    val shade50
        get() = swatch[50]!!;
    val shade100
        get() = swatch[100]!!;
    val shade200
        get() = swatch[200]!!;
    val shade300
        get() = swatch[300]!!;
    val shade400
        get() = swatch[400]!!;
    val shade500
        get() = swatch[500]!!;
    val shade600
        get() = swatch[600]!!;
    val shade700
        get() = swatch[700]!!;
    val shade800
        get() = swatch[800]!!;
    val shade900
        get() = swatch[900]!!;
}

@JvmInline
value class MaterialAccentColor(val swatch: Map<Int, Color>) : ColorSwatch {


    override val default: Color
        get() = swatch[200]!!

    override fun get(index: Int) = swatch[index]!!

    val shade100
        get() = swatch[100]!!;

    val shade200
        get() = swatch[200]!!;

    val shade400
        get() = swatch[400]!!;

    val shade700
        get() = swatch[700]!!;

}