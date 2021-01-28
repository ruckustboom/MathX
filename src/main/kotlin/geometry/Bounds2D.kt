package mathx.geometry

import kotlin.math.max
import kotlin.math.min

/** Axis-Aligned Bound Box in 2D */
public class Bounds2D(
    xMin: Double, xMax: Double,
    yMin: Double, yMax: Double,
) {
    public constructor(min: Point2D, max: Point2D) : this(
        xMin = min.x, xMax = max.x,
        yMin = min.y, yMax = max.y,
    )

    public constructor(x: ClosedRange<Double>, y: ClosedRange<Double>) : this(
        xMin = x.start, xMax = x.endInclusive,
        yMin = y.start, yMax = y.endInclusive,
    )

    public val xMin: Double = min(xMin, xMax)
    public val xMax: Double = max(xMin, xMax)
    public val xCenter: Double get() = (xMin + xMax) / 2.0
    public val xSize: Double get() = xMax - xMin

    public val yMin: Double = min(yMin, yMax)
    public val yMax: Double = max(yMin, yMax)
    public val yCenter: Double get() = (yMin + yMax) / 2.0
    public val ySize: Double get() = yMax - yMin

    public val min: Point2D get() = Point2D(xMin, yMin)
    public val max: Point2D get() = Point2D(xMax, yMax)
    public val center: Point2D get() = Point2D(xCenter, yCenter)
    public val size: Vector2D get() = Vector2D(xSize, ySize)

    public operator fun contains(p: Point2D): Boolean = p.x in xMin..xMax && p.y in xMin..yMax

    public infix fun intersects(b: Bounds2D): Boolean =
        (xMin <= b.xMax && xMax >= b.xMin) && (yMin <= b.yMax && yMax >= b.yMin)

    public operator fun plus(p: Point2D): Bounds2D = Bounds2D(
        xMin = min(xMin, p.x), xMax = max(xMax, p.x),
        yMin = min(yMin, p.y), yMax = max(yMax, p.y),
    )

    public operator fun plus(b: Bounds2D): Bounds2D = Bounds2D(
        xMin = min(xMin, b.xMin), xMax = max(xMax, b.xMax),
        yMin = min(yMin, b.yMin), yMax = max(yMax, b.yMax),
    )

    public operator fun component1(): Double = xMin
    public operator fun component2(): Double = xMax
    public operator fun component3(): Double = yMin
    public operator fun component4(): Double = yMax

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bounds2D

        if (xMin != other.xMin) return false
        if (xMax != other.xMax) return false
        if (yMin != other.yMin) return false
        if (yMax != other.yMax) return false

        return true
    }

    override fun hashCode(): Int {
        var result = xMin.hashCode()
        result = 31 * result + xMax.hashCode()
        result = 31 * result + yMin.hashCode()
        result = 31 * result + yMax.hashCode()
        return result
    }

    override fun toString(): String = "Bounds2D(xMin=$xMin, xMax=$xMax, yMin=$yMin, yMax=$yMax)"

    public companion object {
        @JvmStatic
        public fun of(points: Iterator<Point2D>): Bounds2D {
            var xMin = Double.POSITIVE_INFINITY
            var xMax = Double.NEGATIVE_INFINITY
            var yMin = Double.POSITIVE_INFINITY
            var yMax = Double.NEGATIVE_INFINITY
            for (p in points) {
                if (p.x < xMin) xMin = p.x
                if (p.x > xMax) xMax = p.x
                if (p.y < yMin) yMin = p.y
                if (p.y > yMax) yMax = p.y
            }
            return Bounds2D(
                xMin = xMin, xMax = xMax,
                yMin = yMin, yMax = yMax,
            )
        }

        @JvmStatic
        public fun of(vararg points: Point2D): Bounds2D = of(points.iterator())

        @JvmStatic
        public fun of(points: Iterable<Point2D>): Bounds2D = of(points.iterator())
    }
}
