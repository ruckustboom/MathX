@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

// Trig

public const val PI_F: Float = PI.toFloat()
public const val TAU_F: Float = 2F * PI_F
public const val E_F: Float = E.toFloat()
public const val DEG_PER_RAD_F: Float = 360F / TAU_F

public inline fun degToTurns(deg: Float): Float = deg / 360F
public inline fun turnsToDeg(turns: Float): Float = turns * 360F

public inline fun radToTurns(rad: Float): Float = rad / TAU_F
public inline fun turnsToRad(turns: Float): Float = turns * TAU_F

public inline fun degToRad(deg: Float): Float = deg / DEG_PER_RAD_F
public inline fun radToDeg(rad: Float): Float = rad * DEG_PER_RAD_F

// Rounding

public inline fun based(x: Float, base: Float, calc: (t: Float) -> Float): Float =
    calc(x / base) * base

public inline fun round(x: Float, base: Float): Float = based(x, base, ::round)
public inline fun floor(x: Float, base: Float): Float = based(x, base, ::floor)
public inline fun ceil(x: Float, base: Float): Float = based(x, base, ::ceil)
public inline fun truncate(x: Float, base: Float): Float = based(x, base, ::truncate)
public inline fun nearest(x: Float, a: Float, b: Float): Float = if (abs(x - b) < abs(x - a)) b else a
public inline fun nearest(x: Int, a: Int, b: Int): Int = if (abs(x - b) < abs(x - a)) b else a

// Length

public inline fun length(x: Float, y: Float): Float = sqrt(x * x + y * y)
public inline fun length(x: Float, y: Float, z: Float): Float = sqrt(x * x + y * y + z * z)
public inline fun length(components: FloatArray): Float = sqrt(components.fold(0F) { acc, x -> acc + x * x })

@JvmName("lengthVariadic")
public inline fun length(vararg components: Float): Float = length(components)

public inline fun root(x: Float, base: Float): Float = x.pow(1F / base)

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

public inline fun normalized(x: Float, a: Float, b: Float, calc: (t: Float) -> Float): Float =
    lerp(calc(unlerp(x, a, b)), a, b)

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

public inline fun repeat(x: Float, a: Float, b: Float): Float = normalized(x, a, b, ::repeat)
public inline fun repeat(t: Float): Float = t.mod(1F)

public inline fun reflect(x: Float, a: Float, b: Float): Float = normalized(x, a, b, ::reflect)
public inline fun reflect(t: Float): Float {
    val dist = abs(t) % 2F
    return if (dist < 1F) dist else 2F - dist
}
