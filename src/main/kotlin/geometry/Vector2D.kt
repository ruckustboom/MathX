package mathx.geometry

import mathx.lerp
import kotlin.math.sqrt

public data class Vector2D(val x: Double, val y: Double, val w: Double = 1.0) : Transformation<Vector2D> {
    override val tx: Double get() = x
    override val ty: Double get() = y
    override val tw: Double get() = w

    public operator fun plus(v: Vector2D): Vector2D = Vector2D(x + v.x, y + v.y, w)
    public operator fun minus(v: Vector2D): Vector2D = Vector2D(x - v.x, y - v.y, w)
    public operator fun times(s: Double): Vector2D = Vector2D(x * s, y * s, w)
    public operator fun div(s: Double): Vector2D = Vector2D(x / s, y / s, w)

    @JvmName("negate")
    public operator fun unaryMinus(): Vector2D = Vector2D(-x, -y, w)

    public infix fun distanceTo(v: Vector2D): Double = length(x - v.x, y - v.y)

    public fun length(): Double = length(x, y)

    public fun normalize(): Vector2D {
        val len = length()
        return if (len == 0.0) Vector2D(0.0, 0.0, w) else div(len)
    }

    public infix fun dot(v: Vector2D): Double = x * v.x + y * v.y

    override fun interpolate(b: Vector2D, t: Double): Vector2D = Vector2D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
        w = lerp(w, b.w, t),
    )

    override fun transformBy(t: Transformation<*>): Vector2D = Vector2D(t tx this, t ty this, t tw this)

    public companion object : TransformationCompanion<Vector2D> {
        public val ZERO: Vector2D = Vector2D(0.0, 0.0)
        public val X: Vector2D = x(1.0)
        public val Y: Vector2D = y(1.0)

        public val NEGATIVE_INFINITY: Vector2D = Vector2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)
        public val POSITIVE_INFINITY: Vector2D = Vector2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        public val NaN: Vector2D = Vector2D(Double.NaN, Double.NaN)

        public fun x(x: Double, w: Double = 1.0): Vector2D = Vector2D(x, 0.0, w)
        public fun y(y: Double, w: Double = 1.0): Vector2D = Vector2D(0.0, y, w)

        override fun interpolate(a: Vector2D, b: Vector2D, t: Double): Vector2D = a.interpolate(b, t)

        override fun from(
            xx: Double, xy: Double, xz: Double, xw: Double,
            yx: Double, yy: Double, yz: Double, yw: Double,
            zx: Double, zy: Double, zz: Double, zw: Double,
            tx: Double, ty: Double, tz: Double, tw: Double,
        ): Vector2D = Vector2D(x = tx, y = ty, w = tw)

        override fun toString(): String = "Vector2D"

        private inline fun length(x: Double, y: Double): Double = sqrt(x * x + y * y)
    }
}
