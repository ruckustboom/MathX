@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

// Trig

public const val TAU_F: Float = 2F * PI.toFloat()
public const val RAD_TO_DEG_F: Float = 360F / TAU_F

public inline fun degToRad(deg: Float): Float = deg / RAD_TO_DEG_F
public inline fun radToDeg(rad: Float): Float = rad * RAD_TO_DEG_F

// Rounding

public inline fun round(x: Float, base: Float): Float = round(x / base) * base
public inline fun floor(x: Float, base: Float): Float = floor(x / base) * base
public inline fun ceil(x: Float, base: Float): Float = ceil(x / base) * base
public inline fun nearest(x: Float, a: Float, b: Float): Float = if (abs(x - a) < abs(x - b)) a else b

// Length

public inline fun length(x: Float, y: Float): Float = sqrt(x * x + y * y)
public inline fun length(x: Float, y: Float, z: Float): Float = sqrt(x * x + y * y + z * z)
public inline fun length(components: FloatArray): Float = sqrt(components.fold(0F) { acc, x -> acc + x * x })

@JvmName("lengthVariadic")
public inline fun length(vararg components: Float): Float = length(components)

// Chunks

public inline fun offsetInChunk(x: Float, size: Float, origin: Float = 0F): Float =
    ((x - origin) % size + size) % size

public inline fun startOfChunk(x: Float, size: Float, origin: Float = 0F): Float =
    x - offsetInChunk(x, size, origin)

public inline fun indexOfChunk(x: Float, size: Float, origin: Float = 0F): Float =
    (startOfChunk(x, size, origin) - origin) / size

// Interpolation

public inline fun <T> cerp(from: T, to: T, by: Float, threshold: Float = 1F): T = if (by < threshold) from else to
public inline fun lerp(from: Float, to: Float, by: Float): Float = from + (to - from) * by
public inline fun normalizeIn(x: Float, min: Float, max: Float): Float =
    if (min == max) 0F else (x - min) / (max - min)

public inline fun repeat(x: Float): Float = x.mod(1F)

public inline fun reflect(x: Float): Float {
    val dist = abs(x) % 2F
    return if (dist < 1F) dist else 2F - dist
}

public inline fun repeat(x: Float, min: Float, max: Float): Float =
    lerp(min, max, repeat(normalizeIn(x, min, max)))

public inline fun reflect(x: Float, min: Float, max: Float): Float =
    lerp(min, max, reflect(normalizeIn(x, min, max)))
