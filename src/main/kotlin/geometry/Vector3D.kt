package mathx.geometry

import mathx.lerp
import kotlin.math.sqrt

public data class Vector3D(
    val x: Double,
    val y: Double,
    val z: Double,
    val w: Double = 1.0,
) : Transformation<Vector3D> {
    override val tx: Double get() = x
    override val ty: Double get() = y
    override val tz: Double get() = z
    override val tw: Double get() = w

    public operator fun plus(v: Vector3D): Vector3D = Vector3D(x + v.x, y + v.y, z + v.z, w)
    public operator fun minus(v: Vector3D): Vector3D = Vector3D(x - v.x, y - v.y, z - v.z, w)
    public operator fun times(s: Double): Vector3D = Vector3D(x * s, y * s, z * s, w)
    public operator fun div(s: Double): Vector3D = Vector3D(x / s, y / s, z / s, w)

    @JvmName("negate")
    public operator fun unaryMinus(): Vector3D = Vector3D(-x, -y, -z, w)

    public infix fun distanceTo(v: Vector3D): Double = Companion.length(x - v.x, y - v.y, z - v.z)

    public fun length(): Double = length(x, y, z)

    public fun normalize(): Vector3D {
        val len = length()
        return if (len == 0.0) Vector3D(0.0, 0.0, 0.0, w) else div(len)
    }

    public infix fun dot(v: Vector3D): Double = x * v.x + y * v.y + z * v.z

    public infix fun cross(v: Vector3D): Vector3D = Vector3D(
        x = y * v.z - z * v.y,
        y = z * v.x - x * v.z,
        z = x * v.y - y * v.x,
        w = w,
    )

    override fun interpolate(b: Vector3D, t: Double): Vector3D = Vector3D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
        z = lerp(z, b.z, t),
        w = lerp(w, b.w, t),
    )

    public companion object : TransformationCompanion<Vector3D> {
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

        public fun x(x: Double, w: Double = 1.0): Vector3D = Vector3D(x, 0.0, 0.0, w)
        public fun y(y: Double, w: Double = 1.0): Vector3D = Vector3D(0.0, y, 0.0, w)
        public fun z(z: Double, w: Double = 1.0): Vector3D = Vector3D(0.0, 0.0, z, w)

        override fun interpolate(a: Vector3D, b: Vector3D, t: Double): Vector3D = a.interpolate(b, t)

        private inline fun length(x: Double, y: Double, z: Double): Double = sqrt(x * x + y * y + z * z)

        override fun from(
            xx: Double, xy: Double, xz: Double, xw: Double,
            yx: Double, yy: Double, yz: Double, yw: Double,
            zx: Double, zy: Double, zz: Double, zw: Double,
            tx: Double, ty: Double, tz: Double, tw: Double,
        ): Vector3D = Vector3D(x = tx, y = ty, z = tz, w = tw)

        override fun toString(): String = "Vector3D"
    }
}
