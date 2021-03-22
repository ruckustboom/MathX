package mathx.geometry

import kotlin.math.max
import kotlin.math.min

/** Axis-Aligned Bound Box in 3D */
public class Bounds3D(
    xMin: Double, xMax: Double,
    yMin: Double, yMax: Double,
    zMin: Double, zMax: Double,
) {
    public constructor(min: Vector3D, max: Vector3D) : this(
        xMin = min.x, xMax = max.x,
        yMin = min.y, yMax = max.y,
        zMin = min.z, zMax = max.z,
    )

    public constructor(x: ClosedRange<Double>, y: ClosedRange<Double>, z: ClosedRange<Double>) : this(
        xMin = x.start, xMax = x.endInclusive,
        yMin = y.start, yMax = y.endInclusive,
        zMin = z.start, zMax = z.endInclusive,
    )

    public val xMin: Double = min(xMin, xMax)
    public val xMax: Double = max(xMin, xMax)
    public val xCenter: Double get() = (xMin + xMax) / 2.0
    public val xSize: Double get() = this.xMax - this.xMin

    public val yMin: Double = min(yMin, yMax)
    public val yMax: Double = max(yMin, yMax)
    public val yCenter: Double get() = (yMin + yMax) / 2.0
    public val ySize: Double get() = this.yMax - this.yMin

    public val zMin: Double = min(zMin, zMax)
    public val zMax: Double = max(zMin, zMax)
    public val zCenter: Double get() = (zMin + zMax) / 2.0
    public val zSize: Double get() = this.zMax - this.zMin

    public val min: Vector3D get() = Vector3D(xMin, yMin, zMin)
    public val max: Vector3D get() = Vector3D(xMax, yMax, zMax)
    public val center: Vector3D get() = Vector3D(xCenter, yCenter, zCenter)
    public val size: Vector3D get() = Vector3D(xSize, ySize, zSize)

    public operator fun contains(v: Vector3D): Boolean = v.x in xMin..xMax && v.y in xMin..yMax && v.z in zMin..zMax

    public infix fun intersects(b: Bounds3D): Boolean =
        (xMin <= b.xMax && xMax >= b.xMin) && (yMin <= b.yMax && yMax >= b.yMin) && (zMin <= b.zMax && zMax >= b.zMin)

    public operator fun plus(v: Vector3D): Bounds3D = Bounds3D(
        xMin = min(xMin, v.x), xMax = max(xMax, v.x),
        yMin = min(yMin, v.y), yMax = max(yMax, v.y),
        zMin = min(zMin, v.z), zMax = max(zMax, v.z),
    )

    public operator fun plus(b: Bounds3D): Bounds3D = Bounds3D(
        xMin = min(xMin, b.xMin), xMax = max(xMax, b.xMax),
        yMin = min(yMin, b.yMin), yMax = max(yMax, b.yMax),
        zMin = min(zMin, b.zMin), zMax = max(zMax, b.zMax),
    )

    public operator fun component1(): Double = xMin
    public operator fun component2(): Double = xMax
    public operator fun component3(): Double = yMin
    public operator fun component4(): Double = yMax
    public operator fun component5(): Double = zMin
    public operator fun component6(): Double = zMax

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bounds3D

        if (xMin != other.xMin) return false
        if (xMax != other.xMax) return false
        if (yMin != other.yMin) return false
        if (yMax != other.yMax) return false
        if (zMin != other.zMin) return false
        if (zMax != other.zMax) return false

        return true
    }

    override fun hashCode(): Int {
        var result = xMin.hashCode()
        result = 31 * result + xMax.hashCode()
        result = 31 * result + yMin.hashCode()
        result = 31 * result + yMax.hashCode()
        result = 31 * result + zMin.hashCode()
        result = 31 * result + zMax.hashCode()
        return result
    }

    override fun toString(): String = "Bounds3D(xMin=$xMin, xMax=$xMax, yMin=$yMin, yMax=$yMax, zMin=$zMin, zMax=$zMax)"

    public companion object {
        public val x: ClosedRange<Double> = 0.0..1.0

        @JvmStatic
        public fun of(points: Iterator<Vector3D>): Bounds3D {
            var xMin = Double.POSITIVE_INFINITY
            var xMax = Double.NEGATIVE_INFINITY
            var yMin = Double.POSITIVE_INFINITY
            var yMax = Double.NEGATIVE_INFINITY
            var zMin = Double.POSITIVE_INFINITY
            var zMax = Double.NEGATIVE_INFINITY
            for (p in points) {
                if (p.x < xMin) xMin = p.x
                if (p.x > xMax) xMax = p.x
                if (p.y < yMin) yMin = p.y
                if (p.y > yMax) yMax = p.y
                if (p.z < zMin) zMin = p.z
                if (p.z > zMax) zMax = p.z
            }
            return Bounds3D(
                xMin = xMin, xMax = xMax,
                yMin = yMin, yMax = yMax,
                zMin = zMin, zMax = zMax,
            )
        }

        @JvmStatic
        public fun of(vararg points: Vector3D): Bounds3D = of(points.iterator())

        @JvmStatic
        public fun of(points: Iterable<Vector3D>): Bounds3D = of(points.iterator())
    }
}
