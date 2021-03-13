package mathx.geometry

import mathx.Interpolator
import mathx.lerp
import kotlin.math.sqrt

public data class Vector2D(val x: Double, val y: Double) : Transformation<Vector2D> {
    override val tx: Double get() = x
    override val ty: Double get() = y
    override val tw: Double get() = 0.0

    public operator fun plus(vector: Vector2D): Vector2D = Vector2D(x + vector.x, y + vector.y)
    public operator fun minus(vector: Vector2D): Vector2D = Vector2D(x - vector.x, y - vector.y)
    public operator fun times(scale: Double): Vector2D = Vector2D(x * scale, y * scale)
    public operator fun div(scale: Double): Vector2D = Vector2D(x / scale, y / scale)

    @JvmName("negate")
    public operator fun unaryMinus(): Vector2D = Vector2D(-x, -y)

    public fun length(): Double = sqrt(x * x + y * y)

    public fun normalize(): Vector2D {
        val len = length()
        return if (len == 0.0) ZERO else div(len)
    }

    public fun dot(v: Vector2D): Double = x * v.x + y * v.y

    override fun interpolate(b: Vector2D, t: Double): Vector2D = Vector2D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
    )

    public companion object : Interpolator<Vector2D> {
        public val ZERO: Vector2D = Vector2D(0.0, 0.0)
        public val X: Vector2D = x(1.0)
        public val Y: Vector2D = y(1.0)

        public val NEGATIVE_INFINITY: Vector2D = Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)
        public val POSITIVE_INFINITY: Vector2D = Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        public val NaN: Vector2D = Vector2D(Double.NaN, Double.NaN)

        public fun x(x: Double): Vector2D = Vector2D(x, 0.0)
        public fun y(y: Double): Vector2D = Vector2D(0.0, y)

        override fun interpolate(a: Vector2D, b: Vector2D, t: Double): Vector2D = a.interpolate(b, t)

        override fun toString(): String = "Vector2D"
    }
}
