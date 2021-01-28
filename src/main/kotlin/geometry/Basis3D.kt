package mathx.geometry

import mathx.Interpolator
import mathx.lerp
import kotlin.math.cos
import kotlin.math.sin

public data class Basis3D(
    override val xx: Double, override val xy: Double, override val xz: Double,
    override val yx: Double, override val yy: Double, override val yz: Double,
    override val zx: Double, override val zy: Double, override val zz: Double,
) : Transformation<Basis3D> {
    inline val x: Point3D get() = Point3D(x = xx, y = xy, z = xz)
    inline val y: Point3D get() = Point3D(x = yx, y = yy, z = yz)
    inline val z: Point3D get() = Point3D(x = zx, y = zy, z = zz)

    override fun interpolate(b: Basis3D, t: Double): Basis3D = Basis3D(
        xx = lerp(xx, b.xx, t), xy = lerp(xy, b.xy, t), xz = lerp(xz, b.xz, t),
        yx = lerp(yx, b.yx, t), yy = lerp(yy, b.yy, t), yz = lerp(yz, b.yz, t),
        zx = lerp(zx, b.zx, t), zy = lerp(zy, b.zy, t), zz = lerp(zz, b.zz, t),
    )

    public companion object : Interpolator<Basis3D> {
        public val IDENTITY: Basis3D = Basis3D(
            xx = 1.0, xy = 0.0, xz = 0.0,
            yx = 0.0, yy = 1.0, yz = 0.0,
            zx = 0.0, zy = 0.0, zz = 1.0,
        )

        public fun ypr(euler: Vector3D): Basis3D = ypr(yaw = euler.y, pitch = euler.x, roll = euler.z)
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
                zz = cy * cp,
            )
        }

        public fun yaw(radians: Double): Basis3D = ypr(radians, 0.0, 0.0)
        public fun pitch(radians: Double): Basis3D = ypr(0.0, radians, 0.0)
        public fun roll(radians: Double): Basis3D = ypr(0.0, 0.0, radians)

        override fun interpolate(a: Basis3D, b: Basis3D, t: Double): Basis3D = a.interpolate(b, t)

        override fun toString(): String = "Basis3D"
    }
}

