@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

// Trig

public const val TAU: Double = 2.0 * PI

public const val DEG_IN_TURN: Double = 360.0
public const val RAD_IN_TURN: Double = TAU
public const val GRAD_IN_TURN: Double = 400.0

public inline fun degToTurns(deg: Double): Double = deg / DEG_IN_TURN
public inline fun turnsToDeg(turns: Double): Double = turns * DEG_IN_TURN

public inline fun radToTurns(rad: Double): Double = rad / RAD_IN_TURN
public inline fun turnsToRad(turns: Double): Double = turns * RAD_IN_TURN

public inline fun gradToTurns(grad: Double): Double = grad / GRAD_IN_TURN
public inline fun turnsToGrad(turns: Double): Double = turns * GRAD_IN_TURN

public inline fun degToRad(deg: Double): Double = turnsToRad(degToTurns(deg))
public inline fun radToDeg(rad: Double): Double = turnsToDeg(radToTurns(rad))

public inline fun degToGrad(deg: Double): Double = turnsToGrad(degToTurns(deg))
public inline fun gradToDeg(grad: Double): Double = turnsToDeg(gradToTurns(grad))

public inline fun radToGrad(rad: Double): Double = turnsToGrad(radToTurns(rad))
public inline fun gradToRad(grad: Double): Double = turnsToRad(gradToTurns(grad))

// Rounding

public inline fun round(x: Double, base: Double): Double = round(x / base) * base
public inline fun floor(x: Double, base: Double): Double = floor(x / base) * base
public inline fun ceil(x: Double, base: Double): Double = ceil(x / base) * base
public inline fun nearest(x: Double, a: Double, b: Double): Double = if (abs(x - b) < abs(x - a)) b else a

// Length

public inline fun length(x: Double, y: Double): Double = sqrt(x * x + y * y)
public inline fun length(x: Double, y: Double, z: Double): Double = sqrt(x * x + y * y + z * z)
public inline fun length(components: DoubleArray): Double = sqrt(components.fold(0.0) { acc, x -> acc + x * x })

@JvmName("lengthVariadic")
public inline fun length(vararg components: Double): Double = length(components)

// Chunks

public inline fun chunkOffset(x: Double, size: Double): Double = x.mod(size)
public inline fun chunkStart(x: Double, size: Double): Double = x - chunkOffset(x, size)
public inline fun chunkIndex(x: Double, size: Double): Double = chunkStart(x, size) / size

// Interpolation

/**
 * Constant interpolation between [a] and [b] by ratio [t]
 *
 * If [t] is less than the given [threshold] (default `1.0`) returns [a], otherwise returns [b].
 */
public inline fun <T> cerp(t: Double, a: T, b: T, threshold: Double = 1.0): T = if (t < threshold) a else b

/** Linearly interpolates from [a] to [b] by ratio [t] */
public inline fun lerp(t: Double, a: Double, b: Double): Double = a + (b - a) * t

/**
 * Linearly normalizes [x] in the range from [a] to [b]
 *
 * The inverse of [lerp]
 * ```kotlin
 * x = lerp(t, a, b)
 * t = unlerp(x, a, b)
 * ```
 */
public inline fun unlerp(x: Double, a: Double, b: Double): Double = if (a == b) 0.0 else (x - a) / (b - a)

public inline fun smoothStep(x: Double, a: Double, b: Double): Double = smoothStep(unlerp(x, a, b))
public inline fun smoothStep(t: Double): Double = when {
    t < 0.0 -> 0.0
    t > 1.0 -> 1.0
    else -> t * t * (3.0 - 2.0 * t)
}

public inline fun smootherStep(x: Double, a: Double, b: Double): Double = smootherStep(unlerp(x, a, b))
public inline fun smootherStep(t: Double): Double = when {
    t < 0.0 -> 0.0
    t > 1.0 -> 1.0
    else -> t * t * t * (t * (t * 6.0 - 15.0) + 10.0)
}

public inline fun repeat(x: Double, a: Double, b: Double): Double = lerp(repeat(unlerp(x, a, b)), a, b)
public inline fun repeat(t: Double): Double = t.mod(1.0)

public inline fun reflect(x: Double, a: Double, b: Double): Double = lerp(reflect(unlerp(x, a, b)), a, b)
public inline fun reflect(t: Double): Double {
    val dist = abs(t) % 2.0
    return if (dist < 1.0) dist else 2.0 - dist
}
