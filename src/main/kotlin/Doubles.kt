@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

// Trig

public const val TAU: Double = 2.0 * PI
public const val DEG_PER_RAD: Double = 360.0 / TAU

public inline fun degToTurns(deg: Double): Double = deg / 360.0
public inline fun turnsToDeg(turns: Double): Double = turns * 360.0

public inline fun radToTurns(rad: Double): Double = rad / TAU
public inline fun turnsToRad(turns: Double): Double = turns * TAU

public inline fun degToRad(deg: Double): Double = deg / DEG_PER_RAD
public inline fun radToDeg(rad: Double): Double = rad * DEG_PER_RAD

// Rounding

public inline fun based(x: Double, base: Double, calc: (t: Double) -> Double): Double =
    calc(x / base) * base

public inline fun round(x: Double, base: Double): Double = based(x, base, ::round)
public inline fun floor(x: Double, base: Double): Double = based(x, base, ::floor)
public inline fun ceil(x: Double, base: Double): Double = based(x, base, ::ceil)
public inline fun nearest(x: Double, a: Double, b: Double): Double = if (abs(x - b) < abs(x - a)) b else a
public inline fun nearest(x: Long, a: Long, b: Long): Long = if (abs(x - b) < abs(x - a)) b else a

// Length

public inline fun length(x: Double, y: Double): Double = sqrt(x * x + y * y)
public inline fun length(x: Double, y: Double, z: Double): Double = sqrt(x * x + y * y + z * z)
public inline fun length(components: DoubleArray): Double = sqrt(components.fold(0.0) { acc, x -> acc + x * x })

@JvmName("lengthVariadic")
public inline fun length(vararg components: Double): Double = length(components)

public inline fun root(x: Double, base: Double): Double = x.pow(1.0 / base)

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

public inline fun normalized(x: Double, a: Double, b: Double, calc: (t: Double) -> Double): Double =
    lerp(calc(unlerp(x, a, b)), a, b)

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

public inline fun repeat(x: Double, a: Double, b: Double): Double = normalized(x, a, b, ::repeat)
public inline fun repeat(t: Double): Double = t.mod(1.0)

public inline fun reflect(x: Double, a: Double, b: Double): Double = normalized(x, a, b, ::reflect)
public inline fun reflect(t: Double): Double {
    val dist = abs(t) % 2.0
    return if (dist < 1.0) dist else 2.0 - dist
}
