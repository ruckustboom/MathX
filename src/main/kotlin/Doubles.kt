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
public inline fun roundToPrecision(x: Double, precision: Int): Double = round(x, 1.0 / 10.0.pow(precision))
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
