package mathx.geometry

import mathx.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

public data class Affine3D(
    override val xx: Double, override val xy: Double, override val xz: Double,
    override val yx: Double, override val yy: Double, override val yz: Double,
    override val zx: Double, override val zy: Double, override val zz: Double,
    override val tx: Double, override val ty: Double, override val tz: Double,
) : Transformation<Affine3D> {
    inline val x: Point3D get() = Point3D(x = xx, y = xy, z = xz)
    inline val y: Point3D get() = Point3D(x = yx, y = yy, z = yz)
    inline val z: Point3D get() = Point3D(x = zx, y = zy, z = zz)
    inline val t: Point3D get() = Point3D(x = tx, y = ty, z = tz)

    override fun interpolate(b: Affine3D, t: Double): Affine3D = Affine3D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xz = lerp(xz, b.xz, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yz = lerp(yz, b.yz, t),
        zx = lerp(zx, b.zx, t), zy = lerp(zy, b.zy, t), zz = lerp(zz, b.zz, t),
        tx = lerp(tx, b.tx, t), ty = lerp(ty, b.ty, t), tz = lerp(tz, b.tz, t),
    )

    public companion object : Interpolator<Affine3D> {
        public val IDENTITY: Affine3D = Affine3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
            tx = 0.0, ty = 0.0, tz = 0.0,
        )

        public fun ypr(euler: Vector3D, t: Point3D): Affine3D = ypr(
            yaw = euler.y,
            pitch = euler.x,
            roll = euler.z,
            tx = t.x,
            ty = t.y,
            tz = t.z,
        )

        public fun ypr(yaw: Double, pitch: Double, roll: Double, tx: Double, ty: Double, tz: Double): Affine3D {
            val cy = cos(yaw)
            val sy = sin(yaw)
            val cp = cos(pitch)
            val sp = sin(pitch)
            val cr = cos(roll)
            val sr = sin(roll)

            return Affine3D(
                xx = cy * cr + sy * sp * sr,
                xy = cp * sr,
                xz = -sy * cr + cy * sp * sr,

                yx = cy * -sr + sy * sp * cr,
                yy = cp * cr,
                yz = -sy * -sr + cy * sp * cr,

                zx = sy * cp,
                zy = -sp,
                zz = cy * cp,

                tx = tx,
                ty = ty,
                tz = tz,
            )
        }

        override fun interpolate(a: Affine3D, b: Affine3D, t: Double): Affine3D = a.interpolate(b, t)

        override fun toString(): String = "Affine3D"
    }
}
