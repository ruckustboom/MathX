package mathx.geometry

import mathx.EPSILON
import org.junit.jupiter.api.Assertions.assertEquals

infix fun <T : Transformation<T>> T.shouldBe(expected: T) {
    assertEquals(javaClass, expected.javaClass)

    assertEquals(expected.xx, xx, EPSILON, "xx")
    assertEquals(expected.xy, xy, EPSILON, "xy")
    assertEquals(expected.xz, xz, EPSILON, "xz")
    assertEquals(expected.xw, xw, EPSILON, "xw")

    assertEquals(expected.yx, yx, EPSILON, "yx")
    assertEquals(expected.yy, yy, EPSILON, "yy")
    assertEquals(expected.yz, yz, EPSILON, "yz")
    assertEquals(expected.yw, yw, EPSILON, "yw")

    assertEquals(expected.zx, zx, EPSILON, "zx")
    assertEquals(expected.zy, zy, EPSILON, "zy")
    assertEquals(expected.zz, zz, EPSILON, "zz")
    assertEquals(expected.zw, zw, EPSILON, "zw")

    assertEquals(expected.tx, tx, EPSILON, "tx")
    assertEquals(expected.ty, ty, EPSILON, "ty")
    assertEquals(expected.tz, tz, EPSILON, "tz")
    assertEquals(expected.tw, tw, EPSILON, "tw")
}
