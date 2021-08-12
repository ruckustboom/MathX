@file:JvmName("Interpolation")
@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.abs

public inline fun <T> cerp(from: T, to: T, by: Double, threshold: Double = 1.0): T = if (by < threshold) from else to
public inline fun lerp(from: Double, to: Double, by: Double): Double = from + (to - from) * by
public inline fun normalize(x: Double, min: Double, max: Double): Double =
    if (min == max) 0.0 else (x - min) / (max - min)

public inline fun repeated(x: Double): Double = x.mod(1.0)

public inline fun reflected(x: Double): Double {
    val dist = abs(x) % 2.0
    return if (dist < 1.0) dist else 2.0 - dist
}

public inline fun reflected(x: Double, min: Double, max: Double): Double =
    lerp(min, max, reflected(normalize(x, min, max)))

// Interfaces

public fun interface Interpolated<T> {
    public fun interpolate(b: T, t: Double): T
}

public fun interface Interpolator<T> {
    public fun interpolate(a: T, b: T, t: Double): T
}

public fun interface Easing : Interpolated<Easing> {
    public fun ease(t: Double): Double
    override fun interpolate(b: Easing, t: Double): Easing = Easing { lerp(ease(it), b.ease(it), t) }
}

// Common

public inline fun Easing.easeReversed(t: Double): Double = 1.0 - ease(1.0 - t)
public inline fun Easing.easeWithReverse(t: Double, reverse: Easing = this): Double =
    if (t < 0.5) ease(2.0 * t) / 2.0 else reverse.easeReversed(2.0 * t - 1.0) / 2.0 + 0.5

public inline fun Easing.reversed(): Easing = Easing(::easeReversed)
public inline fun Easing.withReverse(reverse: Easing = this): Easing = Easing { easeWithReverse(it, reverse) }

public inline operator fun Easing.unaryMinus(): Easing = reversed()
public inline operator fun Easing.minus(reverse: Easing): Easing = withReverse(reverse)

public inline fun <T> Interpolated<T>.interpolateEased(b: T, t: Double, easing: Easing): T =
    interpolate(b, easing.ease(t))

public inline fun <T> Interpolator<T>.interpolateEased(a: T, b: T, t: Double, easing: Easing): T =
    interpolate(a, b, easing.ease(t))

public inline fun <T> Interpolated<T>.interpolateEasedReversed(b: T, t: Double, easing: Easing): T =
    interpolate(b, easing.easeReversed(t))

public inline fun <T> Interpolator<T>.interpolateEasedReversed(a: T, b: T, t: Double, easing: Easing): T =
    interpolate(a, b, easing.easeReversed(t))

public inline fun <T> Interpolated<T>.interpolateEasedWithReverse(
    b: T,
    t: Double,
    easing: Easing,
    reverse: Easing = easing,
): T = interpolate(b, easing.easeWithReverse(t, reverse))

public inline fun <T> Interpolator<T>.interpolateEasedWithReverse(
    a: T,
    b: T,
    t: Double,
    easing: Easing,
    reverse: Easing = easing,
): T = interpolate(a, b, easing.easeWithReverse(t, reverse))

public inline infix fun <T> Interpolator<T>.withEasing(easing: Easing): Interpolator<T> =
    Interpolator { a, b, t -> interpolateEased(a, b, t, easing) }
