@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.*

public const val TAU: Double = 2.0 * PI
public const val RAD_TO_DEG: Double = 360.0 / TAU

public inline fun degToRad(deg: Double): Double = deg / RAD_TO_DEG
public inline fun radToDeg(rad: Double): Double = rad * RAD_TO_DEG

public inline fun round(x: Double, base: Double): Double = round(x / base) * base
public inline fun floor(x: Double, base: Double): Double = floor(x / base) * base
public inline fun ceil(x: Double, base: Double): Double = ceil(x / base) * base
public inline fun roundToPrecision(x: Double, precision: Int): Double = round(x, 1.0 / 10.0.pow(precision))
public inline fun nearest(x: Double, a: Double, b: Double): Double = if (abs(x - a) < abs(x - b)) a else b

public inline infix fun Double.pmod(x: Double): Double = (rem(x) + x) % x
public inline fun lerp(a: Double, b: Double, t: Double): Double = a + (b - a) * t
public inline fun unlerp(a: Double, b: Double, x: Double): Double = if (a == b) 0.0 else (x - a) / (b - a)
public inline fun remap(a1: Double, b1: Double, a2: Double, b2: Double, x: Double): Double =
    lerp(a2, b2, unlerp(a1, b1, x))

public inline infix fun Int.pmod(x: Int): Int = (rem(x) + x) % x
public inline fun lerp(a: Int, b: Int, t: Double): Int = lerp(a.toDouble(), b.toDouble(), t).toInt()
public inline fun <T> cerp(a: T, b: T, t: Double): T = if (t < 1.0) a else b

public inline fun repeated(a: Double, b: Double, x: Double): Double = lerp(a, b, repeated(unlerp(a, b, x)))
public inline fun repeated(x: Double): Double = x pmod 1.0

public inline fun reflected(a: Double, b: Double, x: Double): Double = lerp(a, b, reflected(unlerp(a, b, x)))
public inline fun reflected(x: Double): Double {
    val dist = abs(x) % 2.0
    return if (dist < 1.0) dist else 2.0 - dist
}
