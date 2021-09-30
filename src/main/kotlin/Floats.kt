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
public inline fun roundToPrecision(x: Float, precision: Int): Float = round(x, 1F / 10F.pow(precision))
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
