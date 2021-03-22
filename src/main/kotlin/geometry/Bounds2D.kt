package mathx.geometry

import kotlin.math.max
import kotlin.math.min

/** Axis-Aligned Bound Box in 2D */
public class Bounds2D(
    xMin: Double, xMax: Double,
    yMin: Double, yMax: Double,
) : Transformable<Bounds2D> {
    public constructor(min: Vector2D, max: Vector2D) : this(
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

    public val min: Vector2D get() = Vector2D(xMin, yMin)
    public val max: Vector2D get() = Vector2D(xMax, yMax)
    public val center: Vector2D get() = Vector2D(xCenter, yCenter)
    public val size: Vector2D get() = Vector2D(xSize, ySize)

    public operator fun contains(v: Vector2D): Boolean = v.x in xMin..xMax && v.y in xMin..yMax

    public infix fun intersects(b: Bounds2D): Boolean =
        (xMin <= b.xMax && xMax >= b.xMin) && (yMin <= b.yMax && yMax >= b.yMin)

    public operator fun plus(v: Vector2D): Bounds2D = Bounds2D(
        xMin = min(xMin, v.x), xMax = max(xMax, v.x),
        yMin = min(yMin, v.y), yMax = max(yMax, v.y),
    )

    public operator fun plus(b: Bounds2D): Bounds2D = Bounds2D(
        xMin = min(xMin, b.xMin), xMax = max(xMax, b.xMax),
        yMin = min(yMin, b.yMin), yMax = max(yMax, b.yMax),
    )

    override fun transformBy(t: Transformation<*>): Bounds2D = of(
        Vector2D(xMin, yMin) transformBy t,
        Vector2D(xMin, yMax) transformBy t,
        Vector2D(xMax, yMin) transformBy t,
        Vector2D(xMax, yMax) transformBy t,
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
        public fun of(points: Iterator<Vector2D>): Bounds2D {
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
        public fun of(vararg points: Vector2D): Bounds2D = of(points.iterator())

        @JvmStatic
        public fun of(points: Iterable<Vector2D>): Bounds2D = of(points.iterator())
    }
}
