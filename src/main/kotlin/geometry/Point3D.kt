package mathx.geometry

import mathx.Interpolator
import mathx.lerp
import kotlin.math.sqrt

public data class Point3D(val x: Double, val y: Double, val z: Double) : Transformation<Point3D> {
    override val tx: Double get() = x
    override val ty: Double get() = y
    override val tz: Double get() = z

    public operator fun plus(vector: Vector3D): Point3D = Point3D(x + vector.x, y + vector.y, z + vector.z)
    public operator fun minus(vector: Vector3D): Point3D = Point3D(x - vector.x, y - vector.y, z - vector.z)
    public infix fun vectorTo(point: Point3D): Vector3D = Vector3D(point.x - x, point.y - y, point.z - z)

    public infix fun distanceTo(point: Point3D): Double {
        val dx = x - point.x
        val dy = y - point.y
        val dz = z - point.z
        return sqrt(dx * dx + dy * dy + dz * dz)
    }

    override fun interpolate(b: Point3D, t: Double): Point3D = Point3D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
        z = lerp(z, b.z, t),
    )

    public companion object : Interpolator<Point3D> {
        public val ZERO: Point3D = Point3D(0.0, 0.0, 0.0)
        public val X: Point3D = x(1.0)
        public val Y: Point3D = y(1.0)
        public val Z: Point3D = z(1.0)

        public val NEGATIVE_INFINITY: Point3D = Point3D(
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
        )
        public val POSITIVE_INFINITY: Point3D = Point3D(
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
        )
        public val NaN: Point3D = Point3D(Double.NaN, Double.NaN, Double.NaN)

        public fun x(x: Double): Point3D = Point3D(x, 0.0, 0.0)
        public fun y(y: Double): Point3D = Point3D(0.0, y, 0.0)
        public fun z(z: Double): Point3D = Point3D(0.0, 0.0, z)

        override fun interpolate(a: Point3D, b: Point3D, t: Double): Point3D = a.interpolate(b, t)

        override fun toString(): String = "Point3D"
    }
}
