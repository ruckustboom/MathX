@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

// Trig

public const val PI_F: Float = PI.toFloat()
public const val TAU_F: Float = 2F * PI_F
public const val RAD_TO_DEG_F: Float = 360F / TAU_F

public inline fun degToRad(deg: Float): Float = deg / RAD_TO_DEG_F
public inline fun radToDeg(rad: Float): Float = rad * RAD_TO_DEG_F

// Rounding

public inline fun round(x: Float, base: Float): Float = round(x / base) * base
public inline fun floor(x: Float, base: Float): Float = floor(x / base) * base
public inline fun ceil(x: Float, base: Float): Float = ceil(x / base) * base
public inline fun nearest(x: Float, a: Float, b: Float): Float = if (abs(x - b) < abs(x - a)) b else a

// Length

public inline fun length(x: Float, y: Float): Float = sqrt(x * x + y * y)
public inline fun length(x: Float, y: Float, z: Float): Float = sqrt(x * x + y * y + z * z)
public inline fun length(components: FloatArray): Float = sqrt(components.fold(0F) { acc, x -> acc + x * x })

@JvmName("lengthVariadic")
public inline fun length(vararg components: Float): Float = length(components)

// Chunks

public inline fun chunkOffset(x: Float, size: Float): Float = x.mod(size)
public inline fun chunkStart(x: Float, size: Float): Float = x - chunkOffset(x, size)
public inline fun chunkIndex(x: Float, size: Float): Float = chunkStart(x, size) / size

// Interpolation

public inline fun <T> cerp(t: Float, a: T, b: T, threshold: Float = 1F): T = if (t < threshold) a else b
public inline fun lerp(t: Float, a: Float, b: Float): Float = a + (b - a) * t
public inline fun unlerp(x: Float, a: Float, b: Float): Float = if (a == b) 0F else (x - a) / (b - a)

public inline fun smoothStep(x: Float, a: Float, b: Float): Float = smoothStep(unlerp(x, a, b))
public inline fun smoothStep(t: Float): Float = when {
    t < 0F -> 0F
    t > 1F -> 1F
    else -> t * t * (3F - 2F * t)
}

public inline fun smootherStep(x: Float, a: Float, b: Float): Float = smootherStep(unlerp(x, a, b))
public inline fun smootherStep(t: Float): Float = when {
    t < 0.0 -> 0F
    t > 1.0 -> 1F
    else -> t * t * t * (t * (t * 6F - 15F) + 10F)
}

public inline fun repeat(t: Float): Float = t.mod(1F)
public inline fun reflect(t: Float): Float {
    val dist = abs(t) % 2F
    return if (dist < 1F) dist else 2F - dist
}

public inline fun repeat(x: Float, a: Float, b: Float): Float = lerp(repeat(unlerp(x, a, b)), a, b)
public inline fun reflect(x: Float, a: Float, b: Float): Float = lerp(reflect(unlerp(x, a, b)), a, b)
