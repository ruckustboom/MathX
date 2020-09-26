package mathx

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

public data class Point2D(val x: Double, val y: Double) {
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

    public fun lerp(target: Point2D, ratio: Double): Point2D = Point2D(
        x = lerp(x, target.x, ratio),
        y = lerp(y, target.y, ratio),
    )

    public fun normalize(): Point2D {
        val len = length()
        return if (length() == 0.0) ZERO else div(len)
    }

    public companion object {
        public val ZERO: Point2D = Point2D(0.0, 0.0)
        public val X: Point2D = x(1.0)
        public val Y: Point2D = y(1.0)

        public val NEGATIVE_INFINITY: Point2D = Point2D(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)
        public val POSITIVE_INFINITY: Point2D = Point2D(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        public val NaN: Point2D = Point2D(Double.NaN, Double.NaN)

        public fun x(x: Double): Point2D = Point2D(x, 0.0)
        public fun y(y: Double): Point2D = Point2D(0.0, y)
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

public data class Point3D(val x: Double, val y: Double, val z: Double) {
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

    public fun lerp(target: Point3D, ratio: Double): Point3D = Point3D(
        x = lerp(x, target.x, ratio),
        y = lerp(y, target.y, ratio),
        z = lerp(z, target.z, ratio),
    )

    public companion object {
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

public data class Basis(val x: Point3D, val y: Point3D, val z: Point3D) {
    public fun transform(point: Point2D): Point2D = Point2D(
        x = x.x * point.x + y.x * point.y,
        y = x.y * point.x + y.y * point.y,
    )

    public fun transform(point: Point3D): Point3D = Point3D(
        x = x.x * point.x + y.x * point.y + z.x * point.z,
        y = x.y * point.x + y.y * point.y + z.y * point.z,
        z = x.z * point.x + y.z * point.y + z.z * point.z,
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
