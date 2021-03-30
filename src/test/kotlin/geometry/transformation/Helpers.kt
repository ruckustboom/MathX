package mathx.geometry.transformation

import mathx.assertEquals
import kotlin.test.assertEquals

infix fun <T : Transformation<T>> T.shouldBe(expected: T) {
    assertEquals(javaClass, expected.javaClass)

    assertEquals(expected.xx, xx, "xx")
    assertEquals(expected.xy, xy, "xy")
    assertEquals(expected.xz, xz, "xz")
    assertEquals(expected.xw, xw, "xw")

    assertEquals(expected.yx, yx, "yx")
    assertEquals(expected.yy, yy, "yy")
    assertEquals(expected.yz, yz, "yz")
    assertEquals(expected.yw, yw, "yw")

    assertEquals(expected.zx, zx, "zx")
    assertEquals(expected.zy, zy, "zy")
    assertEquals(expected.zz, zz, "zz")
    assertEquals(expected.zw, zw, "zw")

    assertEquals(expected.tx, tx, "tx")
    assertEquals(expected.ty, ty, "ty")
    assertEquals(expected.tz, tz, "tz")
    assertEquals(expected.tw, tw, "tw")
}
