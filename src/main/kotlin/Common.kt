@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.nextDown
import kotlin.math.pow

public const val TAU: Double = 2.0 * PI
public const val RAD_TO_DEG: Double = 360.0 / TAU

public inline fun degToRad(deg: Double): Double = deg / RAD_TO_DEG
public inline fun radToDeg(rad: Double): Double = rad * RAD_TO_DEG

public inline fun round(x: Double, base: Double): Double = kotlin.math.round(x / base) * base
public inline fun floor(x: Double, base: Double): Double = kotlin.math.floor(x / base) * base
public inline fun ceil(x: Double, base: Double): Double = kotlin.math.ceil(x / base) * base
public inline fun roundToPrecision(x: Double, precision: Int): Double = round(x, 1.0 / 10.0.pow(precision))
public inline fun nearest(x: Double, a: Double, b: Double): Double = if (abs(x - a) < abs(x - b)) a else b

public inline infix fun Double.pmod(x: Double): Double = (rem(x) + x) % x
public inline fun lerp(a: Double, b: Double, t: Double): Double = a + (b - a) * t
public inline fun unlerp(a: Double, b: Double, x: Double): Double = if (a == b) 0.0 else (x - a) / (b - a)
public inline fun remap(a1: Double, b1: Double, a2: Double, b2: Double, x: Double): Double =
    lerp(a2, b2, unlerp(a1, b1, x))

public inline fun lerp(a: Int, b: Int, t: Double): Int = lerp(a.toDouble(), b.toDouble(), t).toInt()
public inline fun <T> cerp(a: T, b: T, t: Double): T = if (t < 1.0) a else b

public inline fun reflected(a: Double, b: Double, x: Double): Double = lerp(a, b, reflected(unlerp(a, b, x)))
public inline fun reflected(x: Double): Double {
    val dist = abs(x) % 2.0
    return if (dist < 1.0) dist else 2.0 - dist
}

public inline fun repeated(a: Double, b: Double, x: Double): Double = lerp(a, b, repeated(unlerp(a, b, x)))
public inline fun repeated(x: Double): Double = x pmod 1.0

public val ALMOST_256: Double = 256.0.nextDown()
public inline fun uByteToRatio(x: UByte): Double = x.toInt() / 255.0
public inline fun clampIntToUByte(x: Int): UByte = x.coerceIn(0x00, 0xFF).toUByte()
public inline fun clampUIntToUByte(x: UInt): UByte = x.coerceIn(0x00u, 0xFFu).toUByte()
public inline fun clampRatioToUByte(t: Double): UByte = clampIntToUByte((t * ALMOST_256).toInt())
public inline fun lerp(a: UByte, b: UByte, t: Double): UByte =
    clampRatioToUByte(lerp(uByteToRatio(a), uByteToRatio(b), t))
