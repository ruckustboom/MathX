package mathx.geometry

import mathx.*
import kotlin.math.cos
import kotlin.math.sin

public data class Basis3D(
    val xx: Double, val xy: Double, val xz: Double,
    val yx: Double, val yy: Double, val yz: Double,
    val zx: Double, val zy: Double, val zz: Double,
) : Transformation3D<Basis3D, Basis2D> {
    inline val x: Point3D get() = Point3D(x = xx, y = xy, z = xz)
    inline val y: Point3D get() = Point3D(x = yx, y = yy, z = yz)
    inline val z: Point3D get() = Point3D(x = zx, y = zy, z = zz)

    override fun toTransform(): Transform3D = Transform3D(
        xx = xx, xy = xy, xz = xz, xw = 0.0,
        yx = yx, yy = yy, yz = yz, yw = 0.0,
        zx = zx, zy = zy, zz = zz, zw = 0.0,
        tx = 0.0, ty = 0.0, tz = 0.0, tw = 1.0,
    )

    override fun to2D(): Basis2D = Basis2D(
        xx = xx, xy = xy,
        yx = yx, yy = yy,
    )

    override fun interpolate(b: Basis3D, t: Double): Basis3D = Basis3D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xz = lerp(xz, b.xz, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yz = lerp(yz, b.yz, t),
        zx = lerp(zx, b.zx, t), zy = lerp(zy, b.zy, t), zz = lerp(zz, b.zz, t),
    )

    public companion object : Interpolator<Basis3D> {
        public val IDENT: Basis3D = Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )

        public fun ypr(euler: Point3D): Basis3D = ypr(yaw = euler.y, pitch = euler.x, roll = euler.z)
        public fun ypr(yaw: Double, pitch: Double, roll: Double): Basis3D {
            val cy = cos(yaw)
            val sy = sin(yaw)
            val cp = cos(pitch)
            val sp = sin(pitch)
            val cr = cos(roll)
            val sr = sin(roll)

            return Basis3D(
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

        override fun interpolate(a: Basis3D, b: Basis3D, t: Double): Basis3D = a.interpolate(b, t)

        override fun toString(): String = "Basis3D"
    }
}

