package mathx.geometry.orientation

import mathx.length
import kotlin.math.*

public interface Rotation

public class Euler(
    public val x: Double,
    public val y: Double,
    public val z: Double,
    public val order: AxisOrder,
) : Rotation {
    public enum class AxisOrder
}

public class AxisAngle(
    public val x: Double,
    public val y: Double,
    public val z: Double,
    public val r: Double,
) : Rotation {
    public fun toExponentialMap(): ExponentialMap {
        val len = length(x, y, z)
        return if (len == 0.0) ExponentialMap.IDENTITY else ExponentialMap(x / len * r, y / len * r, z / len * r)
    }

    public companion object {
        public val IDENTITY: AxisAngle = AxisAngle(1.0, 0.0, 0.0, 0.0)
    }
}

// Should this be renamed to RotationVector?
public class ExponentialMap(
    public val x: Double,
    public val y: Double,
    public val z: Double,
) : Rotation {
    public fun toAxisAngle(): AxisAngle {
        val len = length(x, y, z)
        return if (len == 0.0) AxisAngle.IDENTITY else AxisAngle(x / len, y / len, z / len, len)
    }

    public companion object {
        public val IDENTITY: ExponentialMap = ExponentialMap(0.0, 0.0, 0.0)
    }
}

/**
 * Assuming normalized axis of rotation `n` and angle `r`, and the equivalent
 * quaternion `q`:
 * ```
 * q[x] = sin(r / 2) * n[x]
 * q[y] = sin(r / 2) * n[y]
 * q[z] = sin(r / 2) * n[z]
 * q[w] = cos(r / 2)
 * ```
 */
public class Quaternion(
    public val x: Double,
    public val y: Double,
    public val z: Double,
    public val w: Double,
) : Rotation {
    public operator fun unaryMinus(): Quaternion = Quaternion(-x, -y, -z, -w)

    public operator fun times(q: Quaternion): Quaternion = Quaternion(
        x = w * q.x + x * q.w + y * q.z + z * q.y,
        y = w * q.y + y * q.w + z * q.x + x * q.z,
        z = w * q.z + z * q.w + x * q.y + y * q.x,
        w = w * q.w - x * q.x - y * q.y - z * q.z,
    )

    public operator fun times(s: Double): Quaternion = Quaternion(x * s, y * s, z * s, w * s)

    public fun pow(s: Double): Quaternion {
        val a = acos(w)
        if (abs(a % PI) < 0.0001) return this
        val sa = s * a
        val m = sin(sa) / sin(a)
        return Quaternion(x * m, y * m, z * m, cos(sa))
    }

    public fun normalize(): Quaternion {
        val m = magnitude
        return Quaternion(x / m, y / m, z / m, w / m)
    }

    public infix fun dot(q: Quaternion): Double = x * q.x + y * q.y + z * q.z + w * q.w

    public val magnitude: Double get() = sqrt(x * x + y * y + z * z + w * w)

    public fun conjugate(): Quaternion = Quaternion(-x, -y, -z, w)
    public fun inverse(): Quaternion {
        val m = magnitude
        return Quaternion(-x / m, -y / m, -z / m, w / m)
    }

    public fun slerp(b: Quaternion, t: Double): Quaternion {
        val co = dot(b)
        val k0: Double
        val k1: Double
        if (co > 0.9999) {
            k0 = 1.0 - t
            k1 = t
        } else {
            // Compute the sin of the angle using the
            // trig identity sin^2(omega) + cos^2(omega) = 1
            val so = sqrt(1.0 - co * co)

            // Compute the angle from its sine and cosine
            val o = atan2(so, co)

            // Compute inverse of denominator, so we only have
            // to divide once
            val soi = 1.0 / so

            // Compute interpolation parameters
            k0 = sin((1.0 - t) * o) * soi
            k1 = sin(t * o) * soi
        }

        // Interpolate
        return Quaternion(
            x = x * k0 + b.x * k1,
            y = y * k0 + b.y * k1,
            z = z * k0 + b.z * k1,
            w = w * k0 + b.w * k1
        )
    }

    public companion object {
        public val IDENTITY: Quaternion = Quaternion(0.0, 0.0, 0.0, 1.0)
    }
}

public class Quat(
    public val x: Double,
    public val y: Double,
    public val z: Double,
) : Rotation {
    public val w: Double get() = sqrt(1 - x * x - y * y - z * z)

    public operator fun unaryMinus(): Quat = Quat(-x, -y, -z)

    public operator fun times(q: Quat): Quat {
        val w = w
        val qw = q.w
        return Quat(
            x = w * q.x + x * qw + y * q.z + z * q.y,
            y = w * q.y + y * qw + z * q.x + x * q.z,
            z = w * q.z + z * qw + x * q.y + y * q.x,
        )
    }

    public fun inverse(): Quat = Quat(-x, -y, -z)

    public fun pow(s: Double): Quat {
        val w = w
        if (w > 0.9999) return this
        val a = acos(w)
        val sa = s * a
        val m = sin(sa) / sin(a)
        return Quat(x * m, y * m, z * m)
    }

    public companion object {
        public val IDENTITY: Quat = Quat(0.0, 0.0, 0.0)
    }
}
