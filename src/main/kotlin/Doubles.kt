@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

// Trig

public const val TAU: Double = 2.0 * PI
public const val RAD_TO_DEG: Double = 360.0 / TAU

public inline fun degToRad(deg: Double): Double = deg / RAD_TO_DEG
public inline fun radToDeg(rad: Double): Double = rad * RAD_TO_DEG

// Rounding

public inline fun round(x: Double, base: Double): Double = round(x / base) * base
public inline fun floor(x: Double, base: Double): Double = floor(x / base) * base
public inline fun ceil(x: Double, base: Double): Double = ceil(x / base) * base
public inline fun nearest(x: Double, a: Double, b: Double): Double = if (abs(x - a) < abs(x - b)) a else b

// Length

public inline fun length(x: Double, y: Double): Double = sqrt(x * x + y * y)
public inline fun length(x: Double, y: Double, z: Double): Double = sqrt(x * x + y * y + z * z)
public inline fun length(components: DoubleArray): Double = sqrt(components.fold(0.0) { acc, x -> acc + x * x })

@JvmName("lengthVariadic")
public inline fun length(vararg components: Double): Double = length(components)

// Chunks

public inline fun chunkOffset(x: Double, size: Double, origin: Double = 0.0): Double = (x - origin).mod(size)
public inline fun chunkStart(x: Double, size: Double, origin: Double = 0.0): Double = x - chunkOffset(x, size, origin)
public inline fun chunkIndex(x: Double, size: Double, origin: Double = 0.0): Double =
    (chunkStart(x, size, origin) - origin) / size

// Interpolation

public inline fun <T> cerp(t: Double, a: T, b: T, threshold: Double = 1.0): T = if (t < threshold) a else b
public inline fun lerp(t: Double, a: Double, b: Double): Double = a + (b - a) * t
public inline fun unlerp(x: Double, a: Double, b: Double): Double = if (a == b) 0.0 else (x - a) / (b - a)

public inline fun repeat(t: Double): Double = t.mod(1.0)
public inline fun reflect(t: Double): Double {
    val dist = abs(t) % 2.0
    return if (dist < 1.0) dist else 2.0 - dist
}

public inline fun repeat(x: Double, a: Double, b: Double): Double = lerp(repeat(unlerp(x, a, b)), a, b)
public inline fun reflect(x: Double, a: Double, b: Double): Double = lerp(reflect(unlerp(x, a, b)), a, b)
