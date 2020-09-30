package mathx.geometry

import mathx.*
import kotlin.math.sqrt

public data class Point3D(val x: Double, val y: Double, val z: Double) : Transformation, Interpolated<Point3D> {
    public operator fun plus(point: Point3D): Point3D = Point3D(x + point.x, y + point.y, z + point.z)
    public operator fun minus(point: Point3D): Point3D = Point3D(x - point.x, y - point.y, z - point.z)
    public operator fun times(scale: Double): Point3D = Point3D(x * scale, y * scale, z * scale)
    public operator fun div(scale: Double): Point3D = Point3D(x / scale, y / scale, z / scale)
    public operator fun unaryMinus(): Point3D = Point3D(-x, -y, -z)

    public fun dot(point: Point3D): Double = x * point.x + y * point.y + z * point.z
    public fun length(): Double = distance(ZERO)
    public fun distance(point: Point3D): Double {
        val dx = x - point.x
        val dy = y - point.y
        val dz = z - point.z
        return sqrt(dx * dx + dy * dy + dz * dz)
    }

    public fun normalize(): Point3D {
        val len = length()
        return if (length() == 0.0) ZERO else div(len)
    }

    public infix fun cross(point: Point3D): Point3D = Point3D(
        x = y * point.z - z * point.y,
        y = z * point.x - x * point.z,
        z = x * point.y - y * point.x,
    )

    // Transformation

    override fun transform(point: Point2D): Point2D = Point2D(
        x = x + point.x,
        y = y + point.y,
    )

    override fun transform(point: Point3D): Point3D = Point3D(
        x = x + point.x,
        y = y + point.y,
        z = z + point.z,
    )

    override fun transform(basis: Basis): Basis = basis

    override fun toPoint2D(): Point2D = Point2D(
        x = x,
        y = y,
    )

    override fun toPoint3D(): Point3D = this

    override fun toBasis(): Basis = Basis.IDENT

    // Interpolated

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

    }
}

@JvmName("lowerBound3D")
public fun Collection<Point3D>.lowerBoundOrNull(): Point3D? = if (isEmpty()) null else {
    var x = Double.POSITIVE_INFINITY
    var y = Double.POSITIVE_INFINITY
    var z = Double.POSITIVE_INFINITY
    for (point in this) {
        if (point.x < x) x = point.x
        if (point.y < y) y = point.y
        if (point.z < z) z = point.z
    }
    Point3D(x, y, z)
}

@JvmName("upperBound3D")
public fun Collection<Point3D>.upperBoundOrNull(): Point3D? = if (isEmpty()) null else {
    var x = Double.NEGATIVE_INFINITY
    var y = Double.NEGATIVE_INFINITY
    var z = Double.NEGATIVE_INFINITY
    for (point in this) {
        if (point.x > x) x = point.x
        if (point.y > y) y = point.y
        if (point.z > z) z = point.z
    }
    Point3D(x, y, z)
}
