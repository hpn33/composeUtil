//package util.color.jorgecastillo
//
//import androidx.compose.ui.graphics.Color
//import util.color.jorgecastillo.*
//
///**
// * Constructor:
// *
// * Parses the color string, and return the corresponding color-int. If the string cannot be parsed,
// * throws an IllegalArgumentException exception. Supported formats are:
// * #RRGGBB
// * #AARRGGBB
// * The following names are also accepted: red, blue, green, black, white, gray, cyan, magenta,
// * yellow, lightgray, darkgray, grey, lightgrey, darkgrey, aqua, fuchsia, lime, maroon, navy, olive,
// * purple, silver, and teal
// */
//data class HEXColor(val hex: String) {
//    init {
//        // This is to enforce proper parsing. If the hex code does not match the Color.parseColor
//        // format requirements we want it to throw.
////        Color.parseColor(hex)
//    }
//
//    override fun toString(): String {
//        return hex
//    }
//
//    override fun equals(other: Any?): Boolean {
//        return other is HEXColor && asColorInt() == other.asColorInt()
//    }
//}
//
///**
// * Returns the color in complete hex format as in #FFFFFF.
// */
//fun ColorInt.asHex(): HEXColor =
//    HEXColor(String.format("#%06X", 0xFFFFFFFF and this.toLong()))
//
//
////fun HEXColor.asColorInt(): ColorInt = Color.parseColor(hex)
////
////fun HEXColor.asRgb(): RGBColor = asColorInt().asRgb()
////
////fun HEXColor.asArgb(): ARGBColor = asColorInt().asArgb()
////
////fun HEXColor.asCmyk(): CMYKColor = asColorInt().asCmyk()
////
////fun HEXColor.asHsl(): HSLColor = asColorInt().asHsl()
////
////fun HEXColor.asHsla(): HSLAColor = asColorInt().asHsla()
////
////fun HEXColor.asHsv(): HSVColor = asColorInt().asHsv()
//
///**
// * @param value amount to lighten in the range 0...1
// */
////fun HEXColor.lighten(value: Float): HEXColor = this.asColorInt().lighten(value).asHex()
////
/////**
//// * @param value amount to lighten in the range 0...100
//// */
////fun HEXColor.lighten(value: Int): HEXColor = this.asColorInt().lighten(value).asHex()
////
/////**
//// * @param value amount to darken in the range 0...1
//// */
////fun HEXColor.darken(value: Float): HEXColor = this.asColorInt().darken(value).asHex()
////
/////**
//// * @param value amount to darken in the range 0...100
//// */
////fun HEXColor.darken(value: Int): HEXColor = this.asColorInt().darken(value).asHex()
////
/////**
//// * @return a list of shades for the given color like the ones in https://www.color-hex.com/color/e91e63.
//// * Each one of the colors is a HEXColor.
//// *
//// * @param count of shades to generate over the source color. It generates 10 by default.
//// */
////fun HEXColor.shades(count: Int = 10): List<HEXColor> = asColorInt().shades(count).map { it.asHex() }
////
/////**
//// * @return a list of tints for the given color like the ones in https://www.color-hex.com/color/e91e63.
//// * Each one of the colors is a HEXColor.
//// *
//// * @param count of tints to generate over the source color. It generates 10 by default.
//// */
////fun HEXColor.tints(count: Int = 10): List<HEXColor> = asColorInt().tints(count).map { it.asHex() }
////
/////**
//// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
//// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The complimentary color stands for the
//// * color in the opposite side of the circle, so it's (hue + 180) % 360.
//// */
////fun HEXColor.complimentary(): HEXColor = asColorInt().complimentary().asHex()
////
/////**
//// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
//// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The triadic colors stand for 3 colors that
//// * compose a perfect triangle (equal sides) over the circle, so they are equally far from each other.
//// *
//// * Triadic colors for h0 would be (hue + 120) % 360 and (hue + 240) % 360.
//// */
////fun HEXColor.triadic(): Pair<HEXColor, HEXColor> =
////    asColorInt().triadic().let { Pair(it.first.asHex(), it.second.asHex()) }
////
/////**
//// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
//// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The tetradic colors stand for 4 colors
//// * that compose a perfect square (equal sides) over the circle, so they are equally far from each other.
//// *
//// * Tetradic colors for h0 would be (hue + 90) % 360, (hue + 180) % 360 and (hue + 270) % 360.
//// */
////fun HEXColor.tetradic(): Triple<HEXColor, HEXColor, HEXColor> =
////    asColorInt().tetradic().let { Triple(it.first.asHex(), it.second.asHex(), it.third.asHex()) }
////
/////**
//// * The Hue is the colour's position on the colour wheel, expressed in degrees from 0° to 359°, representing the 360° of
//// * the wheel; 0° being red, 180° being red's opposite colour cyan, and so on. The analogous colors stand for 3 colors
//// * that are together in the circle, separated by 30 degrees each.
//// *
//// * Analogous colors for h0 would be (hue + 30) % 360 & (hue - 30) % 360.
//// */
////fun HEXColor.analogous(): Pair<HEXColor, HEXColor> =
////    asColorInt().analogous().let { Pair(it.first.asHex(), it.second.asHex()) }
//
///**
// * Check if a color is dark (convert to XYZ & check Y component)
// */
//// TODO
//
////fun HEXColor.isDark(): Boolean = ColorUtils.calculateLuminance(this.asColorInt()) < 0.5
//
///**
// * Returns a color that contrasts nicely with the one given (receiver). Fallbacks to white and
// * black, but optional light and dark colors can be passed.
// */
////fun HEXColor.contrasting(
////    lightColor: HEXColor = HEXColor("#FFFFFF"),
////    darkColor: HEXColor = HEXColor("#000000")
////) = if (isDark()) {
////    lightColor
////} else {
////    darkColor
////}
