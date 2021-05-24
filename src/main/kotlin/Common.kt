@file:JvmName("MathX")
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

public inline fun length2(x: Double, y: Double): Double = (x * x + y * y)
public inline fun length(x: Double, y: Double): Double = sqrt(length2(x, y))
public inline fun length2(x: Double, y: Double, z: Double): Double = (x * x + y * y + z * z)
public inline fun length(x: Double, y: Double, z: Double): Double = sqrt(length2(x, y, z))
public inline fun length2(components: DoubleArray): Double = components.fold(0.0) { acc, x -> acc + x * x }
public inline fun length(components: DoubleArray): Double = sqrt(length2(components))

@JvmName("length2Variadic")
public inline fun length2(vararg components: Double): Double = length2(components)

@JvmName("lengthVariadic")
public inline fun length(vararg components: Double): Double = length(components)

// Chunks

public inline fun offsetInChunk(x: Double, size: Double, origin: Double = 0.0): Double =
    ((x - origin) % size + size) % size

public inline fun startOfChunk(x: Double, size: Double, origin: Double = 0.0): Double =
    x - offsetInChunk(x, size, origin)

public inline fun indexOfChunk(x: Double, size: Double, origin: Double = 0.0): Double =
    (startOfChunk(x, size, origin) - origin) / size

public inline fun offsetInChunk(x: Int, size: Int, origin: Int = 0): Int =
    ((x - origin) % size + size) % size

public inline fun startOfChunk(x: Int, size: Int, origin: Int = 0): Int =
    x - offsetInChunk(x, size, origin)

public inline fun indexOfChunk(x: Int, size: Int, origin: Int = 0): Int =
    (startOfChunk(x, size, origin) - origin) / size
