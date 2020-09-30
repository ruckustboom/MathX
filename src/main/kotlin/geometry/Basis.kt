package mathx.geometry

import mathx.*
import kotlin.math.cos
import kotlin.math.sin

public data class Basis(val x: Point3D, val y: Point3D, val z: Point3D): Rotation, Interpolated<Basis> {

    // Transformation

    override fun transform(point: Point2D): Point2D = Point2D(
        x = x.x * point.x + y.x * point.y,
        y = x.y * point.x + y.y * point.y,
    )

    override fun transform(point: Point3D): Point3D = Point3D(
        x = x.x * point.x + y.x * point.y + z.x * point.z,
        y = x.y * point.x + y.y * point.y + z.y * point.z,
        z = x.z * point.x + y.z * point.y + z.z * point.z,
    )

    override fun transform(basis: Basis): Basis {
        TODO("Not yet implemented")
    }

    override fun toPoint2D(): Point2D = Point2D.ZERO

    override fun toPoint3D(): Point3D = Point3D.ZERO

    override fun toBasis(): Basis = this

    // Interpolated

    override fun interpolate(b: Basis, t: Double): Basis = Basis(
        x = x.interpolate(b.x, t),
        y = y.interpolate(b.y, t),
        z = z.interpolate(b.z, t),
    )

    public companion object {
        public val IDENT: Basis = Basis(x = Point3D.X, y = Point3D.Y, z = Point3D.Z)

        public fun ypr(euler: Point3D): Basis = ypr(yaw = euler.y, pitch = euler.x, roll = euler.z)
        public fun ypr(yaw: Double, pitch: Double, roll: Double): Basis {
            val cy = cos(yaw)
            val sy = sin(yaw)
            val cp = cos(pitch)
            val sp = sin(pitch)
            val cr = cos(roll)
            val sr = sin(roll)

            return Basis(
                x = Point3D(
                    x = cy * cr + sy * sp * sr,
                    y = cp * sr,
                    z = -sy * cr + cy * sp * sr
                ),
                y = Point3D(
                    x = cy * -sr + sy * sp * cr,
                    y = cp * cr,
                    z = -sy * -sr + cy * sp * cr
                ),
                z = Point3D(
                    x = sy * cp,
                    y = -sp,
                    z = cy * cp
                )
            )
        }
    }
}

public operator fun Basis.invoke(p: Point2D): Point2D = transform(p)
public operator fun Basis.invoke(p: Point3D): Point3D = transform(p)
