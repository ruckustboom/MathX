package mathx.geometry

import mathx.TAU
import kotlin.test.Test
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class TestYPR {
    @Test
    fun testYPR() {
        repeat(100) {
            val angle = TAU * (it / 31.0) - 10.0
            val sin = sin(angle)
            val cos = cos(angle)

            Basis3D.yaw(angle) shouldBe Basis3D(
                xx = cos, xy = 0.0, xz = -sin,
                yx = 0.0, yy = 1.0, yz = 0.0,
                zx = sin, zy = 0.0, zz = cos,
            )
            Basis3D.pitch(angle) shouldBe Basis3D(
                xx = 1.0, xy = 0.0, xz = 0.0,
                yx = 0.0, yy = cos, yz = sin,
                zx = 0.0, zy = -sin, zz = cos,
            )
            Basis3D.roll(angle) shouldBe Basis3D(
                xx = cos, xy = sin, xz = 0.0,
                yx = -sin, yy = cos, yz = 0.0,
                zx = 0.0, zy = 0.0, zz = 1.0,
            )
        }
    }

    @Test
    fun testYPREquivalence() {
        repeat(100) {
            val rot = Vector3D(
                x = Random.nextDouble() * TAU - PI,
                y = Random.nextDouble() * TAU - PI,
                z = Random.nextDouble() * TAU - PI,
            )
            val affine = Affine3D.ypr(rot, rot)
            Basis3D from affine shouldBe Basis3D.ypr(rot)
            Vector3D from affine shouldBe rot
        }
    }

    @Test
    fun testYPROrder() {
        repeat(100) {
            val rot = Vector3D(
                x = Random.nextDouble() * TAU - PI,
                y = Random.nextDouble() * TAU - PI,
                z = Random.nextDouble() * TAU - PI,
            )
            Basis3D.ypr(rot) shouldBe Basis3D.yaw(rot.y).transform(
                Basis3D.pitch(rot.x).transform(
                    Basis3D.roll(rot.z).transform(
                        Basis3D.IDENTITY
                    )
                )
            )
        }
    }
}
