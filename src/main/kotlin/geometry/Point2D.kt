package mathx.geometry

import mathx.*
import kotlin.math.sqrt

public data class Point2D(val x: Double, val y: Double) : Transformation2D<Point2D, Point3D> {
    public operator fun plus(point: Point2D): Point2D = Point2D(x + point.x, y + point.y)
    public operator fun minus(point: Point2D): Point2D = Point2D(x - point.x, y - point.y)
    public operator fun times(scale: Double): Point2D = Point2D(x * scale, y * scale)
    public operator fun div(scale: Double): Point2D = Point2D(x / scale, y / scale)
    public operator fun unaryMinus(): Point2D = Point2D(-x, -y)

    public fun dot(point: Point2D): Double = x * point.x + y * point.y
    public fun length(): Double = distance(ZERO)
    public fun distance(point: Point2D): Double {
        val dx = x - point.x
        val dy = y - point.y
        return sqrt(dx * dx + dy * dy)
    }

    public fun normalize(): Point2D {
        val len = length()
        return if (length() == 0.0) ZERO else div(len)
    }

    override fun toTransform(): Transform2D = Transform2D(
        xx = 1.0, xy = 0.0, xw = 0.0,
        yx = 0.0, yy = 1.0, yw = 0.0,
        tx = x, ty = y, tw = 1.0,
    )

    override fun to3D(): Point3D = Point3D(x=x, y=y, z=0.0)

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

@JvmName("lowerBound2D")
public fun Collection<Point2D>.lowerBoundOrNull(): Point2D? = if (isEmpty()) null else {
    var x = Double.POSITIVE_INFINITY
    var y = Double.POSITIVE_INFINITY
    for (point in this) {
        if (point.x < x) x = point.x
        if (point.y < y) y = point.y
    }
    Point2D(x, y)
}

@JvmName("upperBound3D")
public fun Collection<Point2D>.upperBoundOrNull(): Point2D? = if (isEmpty()) null else {
    var x = Double.NEGATIVE_INFINITY
    var y = Double.NEGATIVE_INFINITY
    for (point in this) {
        if (point.x > x) x = point.x
        if (point.y > y) y = point.y
    }
    Point2D(x, y)
}
