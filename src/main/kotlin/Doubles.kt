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

public inline fun offsetInChunk(x: Double, size: Double, origin: Double = 0.0): Double =
    ((x - origin) % size + size) % size

public inline fun startOfChunk(x: Double, size: Double, origin: Double = 0.0): Double =
    x - offsetInChunk(x, size, origin)

public inline fun indexOfChunk(x: Double, size: Double, origin: Double = 0.0): Double =
    (startOfChunk(x, size, origin) - origin) / size

// Interpolation

public inline fun <T> cerp(from: T, to: T, by: Double, threshold: Double = 1.0): T = if (by < threshold) from else to
public inline fun lerp(from: Double, to: Double, by: Double): Double = from + (to - from) * by
public inline fun normalizeIn(x: Double, min: Double, max: Double): Double =
    if (min == max) 0.0 else (x - min) / (max - min)

public inline fun repeat(x: Double): Double = x.mod(1.0)

public inline fun reflect(x: Double): Double {
    val dist = abs(x) % 2.0
    return if (dist < 1.0) dist else 2.0 - dist
}

public inline fun repeat(x: Double, min: Double, max: Double): Double =
    lerp(min, max, repeat(normalizeIn(x, min, max)))

public inline fun reflect(x: Double, min: Double, max: Double): Double =
    lerp(min, max, reflect(normalizeIn(x, min, max)))
