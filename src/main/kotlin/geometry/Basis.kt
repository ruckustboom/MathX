package mathx.geometry

import mathx.*
import kotlin.math.cos
import kotlin.math.sin

public data class Basis(
    val xx: Double, val xy: Double, val xz: Double,
    val yx: Double, val yy: Double, val yz: Double,
    val zx: Double, val zy: Double, val zz: Double,
) : Rotation, Interpolated<Basis> {
    inline val x: Point3D get() = Point3D(x = xx, y = xy, z = xz)
    inline val y: Point3D get() = Point3D(x = yx, y = yy, z = yz)
    inline val z: Point3D get() = Point3D(x = zx, y = zy, z = zz)

    // Transformation

    override fun transform(point: Point2D): Point2D = Point2D(
        x = xx * point.x + yx * point.y,
        y = xy * point.x + yy * point.y,
    )

    override fun transform(point: Point3D): Point3D = Point3D(
        x = xx * point.x + yx * point.y + zx * point.z,
        y = xy * point.x + yy * point.y + zy * point.z,
        z = xz * point.x + yz * point.y + zz * point.z,
    )

    override fun transform(basis: Basis): Basis {
        TODO("Not yet implemented")
    }

    override fun transform(affine: Affine): Affine {
        TODO("Not yet implemented")
    }

    override fun toPoint2D(): Point2D = Point2D.ZERO

    override fun toPoint3D(): Point3D = Point3D.ZERO

    override fun toBasis(): Basis = this

    override fun toAffine(): Affine = Affine(
        xx = xx, xy = xy, xz = xz,
        yx = yx, yy = yy, yz = yz,
        zx = zx, zy = zy, zz = zz,
        tx = 0.0, ty = 0.0, tz = 0.0,
    )

    // Interpolated

    override fun interpolate(b: Basis, t: Double): Basis = Basis(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xz = lerp(xz, b.xz, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yz = lerp(yz, b.yz, t),
        zx = lerp(zx, b.zx, t), zy = lerp(zy, b.zy, t), zz = lerp(zz, b.zz, t),
    )

    public companion object {
        public val IDENT: Basis = Basis(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )

        public fun ypr(euler: Point3D): Basis = ypr(yaw = euler.y, pitch = euler.x, roll = euler.z)
        public fun ypr(yaw: Double, pitch: Double, roll: Double): Basis {
            val cy = cos(yaw)
            val sy = sin(yaw)
            val cp = cos(pitch)
            val sp = sin(pitch)
            val cr = cos(roll)
            val sr = sin(roll)

            return Basis(
                xx = cy * cr + sy * sp * sr,
                xy = cp * sr,
                xz = -sy * cr + cy * sp * sr,

                yx = cy * -sr + sy * sp * cr,
                yy = cp * cr,
                yz = -sy * -sr + cy * sp * cr,

                zx = sy * cp,
                zy = -sp,
                zz = cy * cp
            )
        }
    }
}

public operator fun Basis.invoke(p: Point2D): Point2D = transform(p)
public operator fun Basis.invoke(p: Point3D): Point3D = transform(p)
