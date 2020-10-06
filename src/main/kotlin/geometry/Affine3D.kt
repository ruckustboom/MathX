package mathx.geometry

import mathx.*

public data class Affine3D(
    val xx: Double, val xy: Double, val xz: Double,
    val yx: Double, val yy: Double, val yz: Double,
    val zx: Double, val zy: Double, val zz: Double,
    val tx: Double, val ty: Double, val tz: Double,
) : Transformation3D<Affine3D> {
    inline val x: Point3D get() = Point3D(x = xx, y = xy, z = xz)
    inline val y: Point3D get() = Point3D(x = yx, y = yy, z = yz)
    inline val z: Point3D get() = Point3D(x = zx, y = zy, z = zz)
    inline val t: Point3D get() = Point3D(x = tx, y = ty, z = tz)

    override fun toTransform3D(): Transform3D = Transform3D(
        xx = xx, xy = xy, xz = xz, xw = 0.0,
        yx = yx, yy = yy, yz = yz, yw = 0.0,
        zx = zx, zy = zy, zz = zz, zw = 0.0,
        tx = tx, ty = ty, tz = tz, tw = 1.0,
    )

    override fun interpolate(b: Affine3D, t: Double): Affine3D = Affine3D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xz = lerp(xz, b.xz, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yz = lerp(yz, b.yz, t),
        zx = lerp(zx, b.zx, t), zy = lerp(zy, b.zy, t), zz = lerp(zz, b.zz, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t), tz = lerp(tz, b.tz, t),
    )

    public companion object : Interpolator<Affine3D> {
        public val IDENT: Affine3D = Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 0.0, ty = 0.0, tz = 1.0,
        )

        override fun interpolate(a: Affine3D, b: Affine3D, t: Double): Affine3D = a.interpolate(b, t)
    }
}
