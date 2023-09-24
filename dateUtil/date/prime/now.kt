package dateUtil.date.prime

import dateUtil.date.kotlinx.nowX


// now
inline fun nowPersian() = nowX().toPersian()

inline fun nowCivil() = nowX().toPrimeCivil()
