package mathx.geometry

import mathx.Interpolator
import mathx.lerp
import kotlin.math.sqrt

public data class Point2D(val x: Double, val y: Double) : Transformation<Point2D> {
    override val tx: Double get() = x
    override val ty: Double get() = y

    public operator fun plus(vector: Vector2D): Point2D = Point2D(x + vector.x, y + vector.y)
    public operator fun minus(vector: Vector2D): Point2D = Point2D(x - vector.x, y - vector.y)
    public infix fun vectorTo(point: Point2D): Vector2D = Vector2D(point.x - x, point.y - y)

    public infix fun distanceTo(point: Point2D): Double {
        val dx = x - point.x
        val dy = y - point.y
        return sqrt(dx * dx + dy * dy)
    }

    override fun interpolate(b: Point2D, t: Double): Point2D = Point2D(
        x = lerp(x, b.x, t),
        y = lerp(y, b.y, t),
    )

    public companion object : Interpolator<Point2D> {
        public val ZERO: Point2D = Point2D(0.0, 0.0)
        public val X: Point2D = x(1.0)
        public val Y: Point2D = y(1.0)

        public val NEGATIVE_INFINITY: Point2D = Point2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)
        public val POSITIVE_INFINITY: Point2D = Point2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        public val NaN: Point2D = Point2D(Double.NaN, Double.NaN)

        public fun x(x: Double): Point2D = Point2D(x, 0.0)
        public fun y(y: Double): Point2D = Point2D(0.0, y)

        override fun interpolate(a: Point2D, b: Point2D, t: Double): Point2D = a.interpolate(b, t)

        override fun toString(): String = "Point2D"
    }
}
