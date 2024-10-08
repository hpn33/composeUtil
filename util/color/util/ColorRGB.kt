package util.color.util

import ColorARGB
import toArgb


data class ColorRGB(
    val red: Int,
    val green: Int,
    val blue: Int
) : ColorAble {

    override fun toColorInt(): ColorInt {
        return ColorInt(rgbToInt(red, green, blue))
    }


    override fun toString(): String {
        return "color(r=$red g=$green b=$blue)"
    }
}

fun ColorInt.toRgb(): ColorRGB {
    val colorTemp = intToARGB(this.value)

    return ColorRGB(
        (colorTemp.red),
        (colorTemp.green),
        (colorTemp.blue)
    )
}


fun ColorRGB.toArgb(): ColorARGB = toColorInt().toArgb()

//
//fun RGBColor.asCmyk(): CMYKColor = asColorInt().asCmyk()
//
//fun RGBColor.asHex(): HEXColor = asColorInt().asHex()
//
fun ColorRGB.asHsl(): ColorHSL = toColorInt().toHSL()
//
//fun RGBColor.asHsla(): HSLAColor = asColorInt().asHsla()
//
//fun RGBColor.asHsv(): HSVColor = asColorInt().asHsv()

///**
// * @param value amount to lighten in the range 0...1
// */
//fun ColorRGB.lighten(value: Float): ColorRGB = this.asColorInt().lighten(value).asRgb()
//
///**
// * @param value amount to lighten in the range 0...100
// */
//fun ColorRGB.lighten(value: Int): ColorRGB = this.asColorInt().lighten(value).asRgb()
//
///**
// * @param value amount to darken in the range 0...1
// */
//fun ColorRGB.darken(value: Float): ColorRGB = this.asColorInt().darken(value).asRgb()
//
///**
// * @param value amount to darken in the range 0...100
// */
//fun ColorRGB.darken(value: Int): ColorRGB = this.asColorInt().darken(value).asRgb()
//
///**
// * @return a list of shades for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a ColorRGB.
// *
// * @param count of shades to generate over the source color. It generates 10 by default.
// */
//fun ColorRGB.shades(count: Int = 10): List<ColorRGB> = asColorInt().shades(count).map { it.asRgb() }
//
///**
// * @return a list of tints for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a ColorRGB.
// *
// * @param count of tints to generate over the source color. It generates 10 by default.
// */
//fun ColorRGB.tints(count: Int = 10): List<ColorRGB> = asColorInt().tints(count).map { it.asRgb() }
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The complimentary color stands for the
// * color in the opposite side of the circle, so it's (hue + 180) % 360.
// */
//fun ColorRGB.complimentary(): ColorRGB = asColorInt().complimentary().asRgb()
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The triadic colors stand for 3 colors that
// * compose a perfect triangle (equal sides) over the circle, so they are equally far from each other.
// *
// * Triadic colors for h0 would be (hue + 120) % 360 and (hue + 240) % 360.
// */
//fun ColorRGB.triadic(): Pair<ColorRGB, ColorRGB> =
//    asColorInt().triadic().let { Pair(it.first.asRgb(), it.second.asRgb()) }
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The tetradic colors stand for 4 colors
// * that compose a perfect square (equal sides) over the circle, so they are equally far from each other.
// *
// * Tetradic colors for h0 would be (hue + 90) % 360, (hue + 180) % 360 and (hue + 270) % 360.
// */
//fun ColorRGB.tetradic(): Triple<ColorRGB, ColorRGB, ColorRGB> =
//    asColorInt().tetradic().let { Triple(it.first.asRgb(), it.second.asRgb(), it.third.asRgb()) }
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The analogous colors stand for 3 colors
// * that are together in the circle, separated by 30 degrees each.
// *
// * Analogous colors for h0 would be (hue + 30) % 360 & (hue - 30) % 360.
// */
//fun ColorRGB.analogous(): Pair<ColorRGB, ColorRGB> =
//    asColorInt().analogous().let { Pair(it.first.asRgb(), it.second.asRgb()) }
//
///**
// * Check if a color is dark (convert to XYZ & check Y component)
// */
//fun ColorRGB.isDark(): Boolean = ColorUtils.calculateLuminance(this.asColorInt()) < 0.5
//
///**
// * Returns a color that contrasts nicely with the one given (receiver). Fallbacks to white and
// * black, but optional light and dark colors can be passed.
// */
//fun ColorRGB.contrasting(
//    lightColor: ColorRGB = ColorRGB(255, 255, 255),
//    darkColor: ColorRGB = ColorRGB(0, 0, 0)
//) = if (isDark()) {
//    lightColor
//} else {
//    darkColor
//}
