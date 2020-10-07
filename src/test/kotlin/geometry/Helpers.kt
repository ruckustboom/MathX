package mathx.geometry

import org.junit.jupiter.api.Assertions.assertEquals

val DELTA = 1e-6

infix fun <T : Transformation<T>> T.shouldBe(expected: T) {
    assertEquals(expected.xx, xx, DELTA)
    assertEquals(expected.xy, xy, DELTA)
    assertEquals(expected.xz, xz, DELTA)
    assertEquals(expected.xw, xw, DELTA)

    assertEquals(expected.yx, yx, DELTA)
    assertEquals(expected.yy, yy, DELTA)
    assertEquals(expected.yz, yz, DELTA)
    assertEquals(expected.yw, yw, DELTA)

    assertEquals(expected.zx, zx, DELTA)
    assertEquals(expected.zy, zy, DELTA)
    assertEquals(expected.zz, zz, DELTA)
    assertEquals(expected.zw, zw, DELTA)

    assertEquals(expected.tx, tx, DELTA)
    assertEquals(expected.ty, ty, DELTA)
    assertEquals(expected.tz, tz, DELTA)
    assertEquals(expected.tw, tw, DELTA)
}
