package util.color.util//package util.color.jorgecastillo
//
//import util.color.jorgecastillo.*
//
///**
// * HSV stands for hue-saturation-value (sometimes also known as HSB, hue-saturation-brightness).
// *
// * Hue is a value from 0...360
// * Saturation is a value from 0...1
// * Lightness is a value from 0...1
// */
//data class HSVColor(
//    val hue: Float,
//    val saturation: Float,
//    val value: Float
//)
//
//
//fun  ColorInt.asHsv(): HSVColor {
//    val hsvArray = FloatArray(3)
//    Color.colorToHSV(this, hsvArray)
//    return HSVColor(hsvArray[0], hsvArray[1], hsvArray[2])
//}
//
//
//fun HSVColor.asColorInt(): ColorInt = Color.HSVToColor(floatArrayOf(hue, saturation, value))
//
//fun HSVColor.asRgb(): RGBColor = asColorInt().asRgb()
//
//fun HSVColor.asArgb(): ARGBColor = asRgb().asArgb()
//
//fun HSVColor.asCmyk(): CMYKColor = asColorInt().asCmyk()
//
//fun HSVColor.asHex(): HEXColor = asColorInt().asHex()
//
//fun HSVColor.asHsl(): HSLColor = asColorInt().asHsl()
//
//fun HSVColor.asHsla(): HSLAColor = asColorInt().asHsla()
//
///**
// * @param value amount to lighten in the range 0...1
// */
//fun HSVColor.lighten(value: Float): HSVColor = this.asColorInt().lighten(value).asHsv()
//
///**
// * @param value amount to lighten in the range 0...100
// */
//fun HSVColor.lighten(value: Int): HSVColor = this.asColorInt().lighten(value).asHsv()
//
///**
// * @param value amount to darken in the range 0...1
// */
//fun HSVColor.darken(value: Float): HSVColor = this.asColorInt().darken(value).asHsv()
//
///**
// * @param value amount to darken in the range 0...100
// */
//fun HSVColor.darken(value: Int): HSVColor = this.asColorInt().darken(value).asHsv()
//
///**
// * @return a list of shades for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a HSLColor.
// *
// * @param count of shades to generate over the source color. It generates 10 by default.
// */
//fun HSVColor.shades(count: Int = 10): List<HSVColor> = asColorInt().shades(count).map { it.asHsv() }
//
///**
// * @return a list of tints for the given color like the ones in https://www.color-hex.com/color/e91e63.
// * Each one of the colors is a HSLColor.
// *
// * @param count of tints to generate over the source color. It generates 10 by default.
// */
//fun HSVColor.tints(count: Int = 10): List<HSVColor> = asColorInt().tints(count).map { it.asHsv() }
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The complimentary color stands for the
// * color in the opposite side of the circle, so it's (hue + 180) % 360.
// */
//fun HSVColor.complimentary(): HSVColor = asColorInt().complimentary().asHsv()
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°,
// * representing the 360° of the wheel; 0° being red, 180° being red's opposite colour cyan, and so
// * on. The triadic colors stand for 3 colors that compose a perfect triangle (equal sides) over the
// * circle, so they are equally far from each other.
// *
// * Triadic colors for h0 would be (hue + 120) % 360 and (hue + 240) % 360.
// */
//fun HSVColor.triadic(): Pair<HSVColor, HSVColor> {
//    val h1 = this.copy(hue = (this.hue + 120) % 360f)
//    val h2 = this.copy(hue = (this.hue + 240) % 360f)
//
//    return Pair(h1, h2)
//}
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The tetradic colors stand for 4 colors
// * that compose a perfect square (equal sides) over the circle, so they are equally far from each other.
// *
// * Tetradic colors for h0 would be (hue + 90) % 360, (hue + 180) % 360 and (hue + 270) % 360.
// */
//fun HSVColor.tetradic(): Triple<HSVColor, HSVColor, HSVColor> {
//    val hue = this.hue // 0° to 359°
//
//    val h1 = this.copy(hue = (hue + 90) % 360)
//    val h2 = this.copy(hue = (hue + 180) % 360)
//    val h3 = this.copy(hue = (hue + 270) % 360)
//
//    return Triple(h1, h2, h3)
//}
//
///**
// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The analogous colors stand for 3 colors
// * that are together in the circle, separated by 30 degrees each.
// *
// * Analogous colors for h0 would be (hue + 30) % 360 & (hue - 30) % 360.
// */
//fun HSVColor.analogous(): Pair<HSVColor, HSVColor> =
//    asColorInt().analogous().let { Pair(it.first.asHsv(), it.second.asHsv()) }
//
///**
// * Check if a color is dark (convert to XYZ & check Y component)
// */
//fun HSVColor.isDark(): Boolean = ColorUtils.calculateLuminance(this.asColorInt()) < 0.5
//
///**
// * Returns a color that contrasts nicely with the one given (receiver). Fallbacks to white and
// * black, but optional light and dark colors can be passed.
// */
//fun HSVColor.contrasting(
//    lightColor: HSVColor = HSVColor(0f, 0f, 1f), // white
//    darkColor: HSVColor = HSVColor(0f, 0f, 0f) // black
//) = if (isDark()) {
//    lightColor
//} else {
//    darkColor
//}
