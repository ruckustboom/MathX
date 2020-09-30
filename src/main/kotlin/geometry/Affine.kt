package mathx.geometry

import mathx.*

public data class Affine(
    val xx: Double, val xy: Double, val xz: Double,
    val yx: Double, val yy: Double, val yz: Double,
    val zx: Double, val zy: Double, val zz: Double,
    val tx: Double, val ty: Double, val tz: Double,
) : Transformation, Interpolated<Affine> {
    inline val x: Point3D get() = Point3D(x = xx, y = xy, z = xz)
    inline val y: Point3D get() = Point3D(x = yx, y = yy, z = yz)
    inline val z: Point3D get() = Point3D(x = zx, y = zy, z = zz)
    inline val t: Point3D get() = Point3D(x = tx, y = ty, z = tz)

    // Transformation

    override fun transform(point: Point2D): Point2D = Point2D(
        x = xx * point.x + yx * point.y + tx,
        y = xy * point.x + yy * point.y + ty,
    )

    override fun transform(point: Point3D): Point3D = Point3D(
        x = xx * point.x + yx * point.y + zx * point.z + tx,
        y = xy * point.x + yy * point.y + zy * point.z + ty,
        z = xz * point.x + yz * point.y + zz * point.z + tz,
    )

    override fun transform(basis: Basis): Basis {
        TODO("Not yet implemented")
    }

    override fun transform(affine: Affine): Affine {
        TODO("Not yet implemented")
    }

    override fun toPoint2D(): Point2D = Point2D(x = tx, y = ty)
    override fun toPoint3D(): Point3D = Point3D(x = tx, y = yy, z = tz)
    override fun toBasis(): Basis = Basis(
        xx = xx, xy = xy, xz = xz,
        yx = yx, yy = yy, yz = yz,
        zx = zx, zy = zy, zz = zz,
    )

    override fun toAffine(): Affine = this

    // Interpolated

    override fun interpolate(b: Affine, t: Double): Affine = Affine(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xz = lerp(xz, b.xz, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yz = lerp(yz, b.yz, t),
        zx = lerp(zx, b.zx, t), zy = lerp(zy, b.zy, t), zz = lerp(zz, b.zz, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t), tz = lerp(tz, b.tz, t),
    )
}
