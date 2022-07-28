@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

// Trig

public const val PI_F: Float = PI.toFloat()
public const val TAU_F: Float = 2F * PI_F

public const val DEGREES_F: Float = 360F
public const val RADIANS_F: Float = TAU_F
public const val GRADIANS_F: Float = 400F

public inline fun degToTurns(deg: Float): Float = deg / DEGREES_F
public inline fun turnsToDeg(turns: Float): Float = turns * DEGREES_F

public inline fun radToTurns(rad: Float): Float = rad / RADIANS_F
public inline fun turnsToRad(turns: Float): Float = turns * RADIANS_F

public inline fun gradToTurns(grad: Float): Float = grad / GRADIANS_F
public inline fun turnsToGrad(turns: Float): Float = turns * GRADIANS_F

public inline fun degToRad(deg: Float): Float = turnsToRad(degToTurns(deg))
public inline fun radToDeg(rad: Float): Float = turnsToDeg(radToTurns(rad))

public inline fun degToGrad(deg: Float): Float = turnsToGrad(degToTurns(deg))
public inline fun gradToDeg(grad: Float): Float = turnsToDeg(gradToTurns(grad))

public inline fun radToGrad(rad: Float): Float = turnsToGrad(radToTurns(rad))
public inline fun gradToRad(grad: Float): Float = turnsToRad(gradToTurns(grad))

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

/**
 * Constant interpolation between [a] and [b] by ratio [t]
 *
 * If [t] is less than the given [threshold] (default `1F`) returns [a], otherwise returns [b].
 */
public inline fun <T> cerp(t: Float, a: T, b: T, threshold: Float = 1F): T = if (t < threshold) a else b

/** Linearly interpolates from [a] to [b] by ratio [t] */
public inline fun lerp(t: Float, a: Float, b: Float): Float = a + (b - a) * t

/**
 * Linearly normalizes [x] in the range from [a] to [b]
 *
 * The inverse of [lerp]
 * ```kotlin
 * x = lerp(t, a, b)
 * t = unlerp(x, a, b)
 * ```
 */
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

public inline fun repeat(x: Float, a: Float, b: Float): Float = lerp(repeat(unlerp(x, a, b)), a, b)
public inline fun repeat(t: Float): Float = t.mod(1F)

public inline fun reflect(x: Float, a: Float, b: Float): Float = lerp(reflect(unlerp(x, a, b)), a, b)
public inline fun reflect(t: Float): Float {
    val dist = abs(t) % 2F
    return if (dist < 1F) dist else 2F - dist
}
