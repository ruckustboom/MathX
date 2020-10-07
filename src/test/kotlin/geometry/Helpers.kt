package mathx.geometry

import org.junit.jupiter.api.Assertions.assertEquals

const val DELTA = 1e-6

infix fun <T : Transformation<T>> T.shouldBe(expected: T) {
    assertEquals(javaClass, expected.javaClass)

    assertEquals(expected.xx, xx, DELTA, "xx")
    assertEquals(expected.xy, xy, DELTA, "xy")
    assertEquals(expected.xz, xz, DELTA, "xz")
    assertEquals(expected.xw, xw, DELTA, "xw")

    assertEquals(expected.yx, yx, DELTA, "yx")
    assertEquals(expected.yy, yy, DELTA, "yy")
    assertEquals(expected.yz, yz, DELTA, "yz")
    assertEquals(expected.yw, yw, DELTA, "yw")

    assertEquals(expected.zx, zx, DELTA, "zx")
    assertEquals(expected.zy, zy, DELTA, "zy")
    assertEquals(expected.zz, zz, DELTA, "zz")
    assertEquals(expected.zw, zw, DELTA, "zw")

    assertEquals(expected.tx, tx, DELTA, "tx")
    assertEquals(expected.ty, ty, DELTA, "ty")
    assertEquals(expected.tz, tz, DELTA, "tz")
    assertEquals(expected.tw, tw, DELTA, "tw")
}
