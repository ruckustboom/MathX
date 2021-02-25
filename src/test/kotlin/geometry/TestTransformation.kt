package mathx.geometry

import mathx.TAU
import kotlin.test.Test
import kotlin.math.PI

class TestTransformation {
    @Test
    fun testBasisYPR() {
        val start = Point3D(3.0, 2.0, 1.0)

        Basis3D.yaw(TAU) shouldBe Basis3D.IDENTITY
        Basis3D.pitch(TAU) shouldBe Basis3D.IDENTITY
        Basis3D.roll(TAU) shouldBe Basis3D.IDENTITY

        Basis3D.yaw(-PI) shouldBe Basis3D.yaw(PI)
        Basis3D.pitch(-PI) shouldBe Basis3D.pitch(PI)
        Basis3D.roll(-PI) shouldBe Basis3D.roll(PI)

        Basis3D.yaw(PI) shouldBe Basis3D(
            xx = -1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = -1.0,
        )

        Basis3D.pitch(PI) shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = -1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = -1.0,
        )

        Basis3D.roll(PI) shouldBe Basis3D(
            xx = -1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = -1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )

        Basis3D.yaw(PI / 2.0) shouldBe Basis3D(
            xx = 0.0, xy = 0.0, xz = -1.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 1.0, zy = 0.0, zz = 0.0,
        )

        Basis3D.pitch(PI / 2.0) shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 0.0, yz = 1.0,
            zx = 0.0, zy = -1.0, zz = 0.0,
        )

        Basis3D.roll(PI / 2.0) shouldBe Basis3D(
            xx = 0.0, xy = 1.0, xz = 0.0,
            yx = -1.0, yy = 0.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )

        Basis3D.yaw(-PI / 2.0) shouldBe Basis3D(
            xx = 0.0, xy = 0.0, xz = 1.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = -1.0, zy = 0.0, zz = 0.0,
        )

        Basis3D.pitch(-PI / 2.0) shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 0.0, yz = -1.0,
            zx = 0.0, zy = 1.0, zz = 0.0,
        )

        Basis3D.roll(-PI / 2.0) shouldBe Basis3D(
            xx = 0.0, xy = -1.0, xz = 0.0,
            yx = 1.0, yy = 0.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )

        Basis3D.yaw(PI / 2.0) transform start shouldBe Point3D(1.0, 2.0, -3.0)
        Basis3D.pitch(PI / 2.0) transform start shouldBe Point3D(3.0, -1.0, 2.0)
        Basis3D.roll(PI / 2.0) transform start shouldBe Point3D(-2.0, 3.0, 1.0)
        Basis3D.yaw(-PI / 2.0) transform start shouldBe Point3D(-1.0, 2.0, 3.0)
        Basis3D.pitch(-PI / 2.0) transform start shouldBe Point3D(3.0, 1.0, -2.0)
        Basis3D.roll(-PI / 2.0) transform start shouldBe Point3D(2.0, -3.0, 1.0)
    }
}
