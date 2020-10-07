package mathx.geometry

import org.junit.jupiter.api.Test

class TestConversion {
    @Test
    fun testTransform3D() {
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        )
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        )
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 15.0)
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 15.0)

        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        )
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 8.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 12.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 16.0,
        ).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testAffine3D() {
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 0.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 1.0,
        )
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        )
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 15.0)
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 15.0)

        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 1.0,
        )
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        ).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testBasis3D() {
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 3.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 7.0, yw = 0.0,
            zx = 9.0, zy = 10.0, zz = 11.0, zw = 0.0,
            tx = 0.0, ty = 0.0, tz = 0.0, tw = 1.0,
        )
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
            tx = 0.0, ty = 0.0, tz = 0.0,
        )
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        )
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toPoint3D() shouldBe Point3D(x = 0.0, y = 0.0, z = 0.0)
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toVector3D() shouldBe Vector3D(x = 0.0, y = 0.0, z = 0.0)

        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 0.0, ty = 0.0, tw = 1.0,
        )
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 0.0, ty = 0.0,
        )
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toPoint2D() shouldBe Point2D(x = 0.0, y = 0.0)
        Basis3D(
            xx = 1.0, xy = 2.0, xz = 3.0,
            yx = 5.0, yy = 6.0, yz = 7.0,
            zx = 9.0, zy = 10.0, zz = 11.0,
        ).toVector2D() shouldBe Vector2D(x = 0.0, y = 0.0)
    }

    @Test
    fun testPoint3D() {
        Point3D(x = 13.0, y = 14.0, z = 15.0).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 1.0,
        )
        Point3D(x = 13.0, y = 14.0, z = 15.0).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Point3D(x = 13.0, y = 14.0, z = 15.0).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Point3D(x = 13.0, y = 14.0, z = 15.0).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 15.0)
        Point3D(x = 13.0, y = 14.0, z = 15.0).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 15.0)

        Point3D(x = 13.0, y = 14.0, z = 15.0).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 1.0,
        )
        Point3D(x = 13.0, y = 14.0, z = 15.0).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 13.0, ty = 14.0,
        )
        Point3D(x = 13.0, y = 14.0, z = 15.0).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )
        Point3D(x = 13.0, y = 14.0, z = 15.0).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Point3D(x = 13.0, y = 14.0, z = 15.0).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testVector3D() {
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 15.0, tw = 0.0,
        )
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 15.0,
        )
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 15.0)
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 15.0)

        Vector3D(x = 13.0, y = 14.0, z = 15.0).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 0.0,
        )
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 13.0, ty = 14.0,
        )
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Vector3D(x = 13.0, y = 14.0, z = 15.0).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testTransform2D() {
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 0.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yz = 0.0, yw = 8.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 0.0, tw = 16.0,
        )
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 0.0,
        )
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 0.0)
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 0.0)

        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        )
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Transform2D(
            xx = 1.0, xy = 2.0, xw = 4.0,
            yx = 5.0, yy = 6.0, yw = 8.0,
            tx = 13.0, ty = 14.0, tw = 16.0,
        ).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testAffine2D() {
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 0.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 0.0, tw = 1.0,
        )
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 0.0,
        )
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 0.0)
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 0.0)

        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 1.0,
        )
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        )
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 13.0, ty = 14.0,
        ).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testBasis2D() {
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 2.0, xz = 0.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 0.0, ty = 0.0, tz = 0.0, tw = 1.0,
        )
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 0.0, ty = 0.0, tz = 0.0,
        )
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 2.0, xz = 0.0,
            yx = 5.0, yy = 6.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toPoint3D() shouldBe Point3D(x = 0.0, y = 0.0, z = 0.0)
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toVector3D() shouldBe Vector3D(x = 0.0, y = 0.0, z = 0.0)

        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 2.0, xw = 0.0,
            yx = 5.0, yy = 6.0, yw = 0.0,
            tx = 0.0, ty = 0.0, tw = 1.0,
        )
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
            tx = 0.0, ty = 0.0,
        )
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        )
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toPoint2D() shouldBe Point2D(x = 0.0, y = 0.0)
        Basis2D(
            xx = 1.0, xy = 2.0,
            yx = 5.0, yy = 6.0,
        ).toVector2D() shouldBe Vector2D(x = 0.0, y = 0.0)
    }

    @Test
    fun testPoint2D() {
        Point2D(x = 13.0, y = 14.0).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 0.0, tw = 1.0,
        )
        Point2D(x = 13.0, y = 14.0).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 0.0,
        )
        Point2D(x = 13.0, y = 14.0).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Point2D(x = 13.0, y = 14.0).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 0.0)
        Point2D(x = 13.0, y = 14.0).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 0.0)

        Point2D(x = 13.0, y = 14.0).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 1.0,
        )
        Point2D(x = 13.0, y = 14.0).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 13.0, ty = 14.0,
        )
        Point2D(x = 13.0, y = 14.0).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )
        Point2D(x = 13.0, y = 14.0).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Point2D(x = 13.0, y = 14.0).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }

    @Test
    fun testVector2D() {
        Vector2D(x = 13.0, y = 14.0).toTransform3D() shouldBe Transform3D(
            xx = 1.0, xy = 0.0, xz = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0, yw = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0, zw = 0.0,
            tx = 13.0, ty = 14.0, tz = 0.0, tw = 0.0,
        )
        Vector2D(x = 13.0, y = 14.0).toAffine3D() shouldBe Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 13.0, ty = 14.0, tz = 0.0,
        )
        Vector2D(x = 13.0, y = 14.0).toBasis3D() shouldBe Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )
        Vector2D(x = 13.0, y = 14.0).toPoint3D() shouldBe Point3D(x = 13.0, y = 14.0, z = 0.0)
        Vector2D(x = 13.0, y = 14.0).toVector3D() shouldBe Vector3D(x = 13.0, y = 14.0, z = 0.0)

        Vector2D(x = 13.0, y = 14.0).toTransform2D() shouldBe Transform2D(
            xx = 1.0, xy = 0.0, xw = 0.0,
            yx = 0.0, yy = 1.0, yw = 0.0,
            tx = 13.0, ty = 14.0, tw = 0.0,
        )
        Vector2D(x = 13.0, y = 14.0).toAffine2D() shouldBe Affine2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
            tx = 13.0, ty = 14.0,
        )
        Vector2D(x = 13.0, y = 14.0).toBasis2D() shouldBe Basis2D(
            xx = 1.0, xy = 0.0,
            yx = 0.0, yy = 1.0,
        )
        Vector2D(x = 13.0, y = 14.0).toPoint2D() shouldBe Point2D(x = 13.0, y = 14.0)
        Vector2D(x = 13.0, y = 14.0).toVector2D() shouldBe Vector2D(x = 13.0, y = 14.0)
    }
}
