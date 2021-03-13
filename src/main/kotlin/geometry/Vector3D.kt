package mathx.geometry

import mathx.Interpolator
import mathx.lerp
import kotlin.math.sqrt

public data class Vector3D(val x: Double, val y: Double, val z: Double) : Transformation<Vector3D> {
    override val tx: Double get() = x
    override val ty: Double get() = y
    override val tz: Double get() = z
    override val tw: Double get() = 0.0

    public operator fun plus(vector: Vector3D): Vector3D = Vector3D(x + vector.x, y + vector.y, z + vector.z)
    public operator fun minus(vector: Vector3D): Vector3D = Vector3D(x - vector.x, y - vector.y, z - vector.z)
    public operator fun times(scale: Double): Vector3D = Vector3D(x * scale, y * scale, z * scale)
    public operator fun div(scale: Double): Vector3D = Vector3D(x / scale, y / scale, z / scale)

    @JvmName("negate")
    public operator fun unaryMinus(): Vector3D = Vector3D(-x, -y, -z)

    public fun length(): Double = sqrt(x * x + y * y + z * z)

    public fun normalize(): Vector3D {
        val len = length()
        return if (len == 0.0) ZERO else div(len)
    }

    public fun dot(vector: Vector3D): Double = x * vector.x + y * vector.y + z * vector.z

    public infix fun cross(vector: Vector3D): Vector3D = Vector3D(
        x = y * vector.z - z * vector.y,
        y = z * vector.x - x * vector.z,
        z = x * vector.y - y * vector.x,
    )

    override fun interpolate(b: Vector3D, t: Double): Vector3D = Vector3D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
        z = lerp(z, b.z, t),
    )

    public companion object : Interpolator<Vector3D> {
        public val ZERO: Vector3D = Vector3D(0.0, 0.0, 0.0)
        public val X: Vector3D = x(1.0)
        public val Y: Vector3D = y(1.0)
        public val Z: Vector3D = z(1.0)

        public val NEGATIVE_INFINITY: Vector3D = Vector3D(
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY,
        )
        public val POSITIVE_INFINITY: Vector3D = Vector3D(
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY,
        )
        public val NaN: Vector3D = Vector3D(Double.NaN, Double.NaN, Double.NaN)

        public fun x(x: Double): Vector3D = Vector3D(x, 0.0, 0.0)
        public fun y(y: Double): Vector3D = Vector3D(0.0, y, 0.0)
        public fun z(z: Double): Vector3D = Vector3D(0.0, 0.0, z)

        override fun interpolate(a: Vector3D, b: Vector3D, t: Double): Vector3D = a.interpolate(b, t)

        override fun toString(): String = "Vector3D"
    }
}
