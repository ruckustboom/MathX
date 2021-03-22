package mathx.geometry

import kotlin.test.Test

class TestConversion {
    @Test
    fun testTransform3D() {
        val origin = Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        )
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 13.0, y = 14.0, z = 15.0, w = 16.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 13.0, y = 14.0, w = 16.0)
    }

    @Test
    fun testAffine3D() {
        val origin = Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 0.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 1.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 13.0, y = 14.0, z = 15.0, w = 1.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 1.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testBasis3D() {
        val origin = Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        )
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 0.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 0.0,
            tx = 0.0, ty = 0.0, tz = 0.0, tw = 1.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 0.0, ty = 0.0, tz = 0.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 0.0, y = 0.0, z = 0.0, w = 1.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 0.0, ty = 0.0, tw = 1.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 0.0, ty = 0.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 0.0, y = 0.0)
    }

    @Test
    fun testVector3D() {
        val origin = Vector3D(x = 13.0, y = 14.0, z = 15.0, w = 16.0)
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 13.0, y = 14.0, z = 15.0, w = 16.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 13.0, ty = 14.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 13.0, y = 14.0, w = 16.0)
    }

    @Test
    fun testTransform2D() {
        val origin = Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        )
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 0.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 0.0, yw = 8.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 0.0, tw = 16.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 0.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 13.0, y = 14.0, z = 0.0, w = 16.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 13.0, y = 14.0, w = 16.0)
    }

    @Test
    fun testAffine2D() {
        val origin = Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 0.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 0.0, tw = 1.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 0.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 13.0, y = 14.0, z = 0.0, w = 1.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 1.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testBasis2D() {
        val origin = Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 0.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 0.0, ty = 0.0, tz = 0.0, tw = 1.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 0.0, ty = 0.0, tz = 0.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 0.0, y = 0.0, z = 0.0, w = 1.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 0.0, ty = 0.0, tw = 1.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 0.0, ty = 0.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 0.0, y = 0.0)
    }

    @Test
    fun testVector2D() {
        val origin = Vector2D(x = 13.0, y = 14.0, w = 15.0)
        Transform3D from origin shouldBe Transform3D(
            xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 0.0, tw = 15.0,
        )
        Affine3D from origin shouldBe Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 0.0,
        )
        Basis3D from origin shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Vector3D from origin shouldBe Vector3D(x = 13.0, y = 14.0, z = 0.0, w = 15.0)

        Transform2D from origin shouldBe Transform2D(
            xx = 1.0, xy = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 15.0,
        )
        Affine2D from origin shouldBe Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 13.0, ty = 14.0,
        )
        Basis2D from origin shouldBe Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )
        Vector2D from origin shouldBe Vector2D(x = 13.0, y = 14.0, w = 15.0)
    }
}
