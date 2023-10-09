package util.dateUtil.date.prime

import util.dateUtil.date.kotlinx.nowX


// now
inline fun nowPersian() = nowX().toPersian()

inline fun nowCivil() = nowX().toPrimeCivil()
