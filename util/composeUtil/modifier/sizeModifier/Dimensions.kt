package util.composeUtil.modifier.sizeModifier


//import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp

sealed class Dimensions {
    object Width : Dimensions()
    object Height : Dimensions()

    sealed class DimensionOperator {
        object LessThan : DimensionOperator()
        object GreaterThan : DimensionOperator()
    }

    class DimensionComparator(
        val operator: DimensionOperator,
        private val dimension: Dimensions,
        val value: Dp,
    ) {

        fun compare(width: Dp, height: Dp): Boolean {
            return when (dimension) {
                Width -> when (operator) {
                    DimensionOperator.LessThan -> width < value
                    DimensionOperator.GreaterThan -> width > value
                }

                Height -> when (operator) {
                    DimensionOperator.LessThan -> height < value
                    DimensionOperator.GreaterThan -> height > value
                }
            }
        }
    }
}


infix fun Dimensions.lessThan(value: Dp): Dimensions.DimensionComparator {
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        dimension = this,
        value = value
    )
}

infix fun Dimensions.greaterThan(value: Dp): Dimensions.DimensionComparator {
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.GreaterThan,
        dimension = this,
        value = value
    )
}