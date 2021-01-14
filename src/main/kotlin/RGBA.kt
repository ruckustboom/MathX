@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.nextDown

public inline fun rgba(red: Int, green: Int, blue: Int, alpha: Int = 0xFF): Int =
    (red.coerceIn(0x00, 0xFF) shl 24) or
            (green.coerceIn(0x00, 0xFF) shl 16) or
            (blue.coerceIn(0x00, 0xFF) shl 8) or
            alpha.coerceIn(0x00, 0xFF)

public inline fun gray(gray: Int = 0x80, alpha: Int = 0xFF): Int = rgba(gray, gray, gray, alpha)

public inline fun getRed(rgba: Int): Int = rgba ushr 24 and 0xFF
public inline fun getGreen(rgba: Int): Int = rgba ushr 16 and 0xFF
public inline fun getBlue(rgba: Int): Int = rgba ushr 8 and 0xFF
public inline fun getAlpha(rgba: Int): Int = rgba and 0xFF
public inline fun getGray(rgba: Int): Double =
    getRed(rgba) / 255.0 * 0.2126 +
            getGreen(rgba) / 255.0 * 0.7125 +
            getBlue(rgba) / 255.0 * 0.0722

public inline fun copy(
    rgba: Int,
    red: Int = getRed(rgba),
    green: Int = getGreen(rgba),
    blue: Int = getBlue(rgba),
    alpha: Int = getAlpha(rgba),
): Int = rgba(red, green, blue, alpha)

public inline fun toGrayscale(rgba: Int): Int = gray(toChannel(getGray(rgba)), getAlpha(rgba))
public inline fun invert(rgba: Int): Int = rgba(
    red = 255 - getRed(rgba),
    green = 255 - getGreen(rgba),
    blue = 255 - getBlue(rgba),
    alpha = getAlpha(rgba),
)

public fun toHex(rgba: Int): String = buildString {
    val r = getRed(rgba).toString(16)
    if (r.length == 1) append('0')
    append(r)
    val g = getGreen(rgba).toString(16)
    if (g.length == 1) append('0')
    append(g)
    val b = getBlue(rgba).toString(16)
    if (b.length == 1) append('0')
    append(b)
    val a = getAlpha(rgba).toString(16)
    if (a.length == 1) append('0')
    append(a)
}

public object PreMultipliedInterpolator : Interpolator<Int> {
    override fun interpolate(a: Int, b: Int, t: Double): Int {
        val aa = getAlpha(a) / 255.0
        val ba = getAlpha(b) / 255.0
        val ta = lerp(aa, ba, t)
        return if (ta == 0.0) 0 else rgba(
            red = toChannel(lerp(getRed(a) / 255.0 * aa, getRed(b) / 255.0 * ba, t) / ta),
            green = toChannel(lerp(getGreen(a) / 255.0 * aa, getGreen(b) / 255.0 * ba, t) / ta),
            blue = toChannel(lerp(getBlue(a) / 255.0 * aa, getBlue(b) / 255.0 * ba, t) / ta),
            alpha = toChannel(ta)
        )
    }
}

public object StraightAlphaInterpolator : Interpolator<Int> {
    override fun interpolate(a: Int, b: Int, t: Double): Int = rgba(
        red = lerp(getRed(a), getRed(b), t),
        green = lerp(getGreen(a), getGreen(b), t),
        blue = lerp(getBlue(a), getBlue(b), t),
        alpha = lerp(getAlpha(a), getAlpha(b), t),
    )
}

// Impl

@PublishedApi
internal inline fun toChannel(ratio: Double): Int = (ratio.coerceIn(0.0, 1.0.nextDown()) * 256.0).toInt()
